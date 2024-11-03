package com.demo.accounts.controller;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.accounts.model.Cuenta;
import com.demo.accounts.model.TipoCuenta;

import com.demo.accounts.service.TipoCuentaService;

@CrossOrigin
@RestController
@RequestMapping("/tipocuenta")
public class TipoCuentaController {


	
	@Autowired
	private TipoCuentaService tipoCuentaService;
	
	public TipoCuentaController ( TipoCuentaService tipoCuentaService) {

		this.tipoCuentaService = tipoCuentaService;
	}

	@GetMapping("")
	public ResponseEntity<Page<TipoCuenta> >getTiposCuenta(Pageable pageable){
		Page<TipoCuenta> tipos = tipoCuentaService.listTiposCuenta(pageable);
		if(tipos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(tipos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<TipoCuenta>> getTipoCuenta(@PathVariable Long id) {
		Optional<TipoCuenta> tipoCuenta = tipoCuentaService.findById(id);
		if(tipoCuenta!=null) {
			return ResponseEntity.ok(tipoCuenta);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	//Probando ResponseEntity y Page
	@GetMapping("/{id}/{estado}")
	public ResponseEntity<Page<Cuenta>> filtraCuentasDisponibles(@PathVariable Long id, @PathVariable String estado, Pageable pageable) {
		Page<Cuenta> filtrados = tipoCuentaService.filtraCuentas(id, estado, pageable);
		if(filtrados.isEmpty()) {
			return ResponseEntity.noContent().build();
			
		}
		return ResponseEntity.ok(filtrados);
		/*codigo antiguo:
		 * System.out.println(estado);
		return tipoCuentaService.filtraCuentas(id, estado);
		*/
	}
	
	@PostMapping("/nuevo")
	public ResponseEntity<TipoCuenta> createTipoCuenta(@RequestBody TipoCuenta tipoCuenta) {
		tipoCuentaService.save(tipoCuenta);
		return new ResponseEntity<TipoCuenta>(tipoCuenta, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TipoCuenta> updateTipoCuenta(@RequestBody TipoCuenta tipoCuentaUpdated, @PathVariable Long id) {
		return tipoCuentaService.findById(id)
				.map(tipoCuenta->{
					tipoCuenta.setNombre(tipoCuentaUpdated.getNombre());
					tipoCuentaService.save(tipoCuenta);
					return ResponseEntity.ok(tipoCuenta);
				}).orElseGet(()->{
					tipoCuentaUpdated.setId(id);
					

					tipoCuentaService.save(tipoCuentaUpdated);
					return new ResponseEntity<TipoCuenta>(tipoCuentaUpdated, HttpStatus.CREATED);
				});
		
		
	}
	
	
	
    @DeleteMapping("/{id}")
    public ResponseEntity<TipoCuenta> deleteTipoCuenta(@PathVariable Long id) {
        tipoCuentaService.delete(id);
        return ResponseEntity.ok().build();
    }
	
}
