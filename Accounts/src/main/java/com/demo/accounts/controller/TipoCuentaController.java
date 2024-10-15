package com.demo.accounts.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.demo.accounts.repository.TipoCuentaRepository;
import com.demo.accounts.service.TipoCuentaService;

@CrossOrigin
@RestController
@RequestMapping("/tipocuenta")
public class TipoCuentaController {

	@Autowired
	private TipoCuentaRepository tipoCuentaRepository;
	
	@Autowired
	private TipoCuentaService tipoCuentaService;
	
	public TipoCuentaController (TipoCuentaRepository tipoCuentaRepository) {
		this.tipoCuentaRepository= tipoCuentaRepository;
	}

	@GetMapping("")
	public List<TipoCuenta> getTiposCuenta(){
		return tipoCuentaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public TipoCuenta getTipoCuenta(@PathVariable Long id) {
		return tipoCuentaRepository.findById(id).orElseThrow(RuntimeException::new);
	}
	
	@GetMapping("/{id}/{estado}")
	public List<Cuenta> filtraCuentasDisponibles(@PathVariable Long id, @PathVariable String estado) {
		System.out.println(estado);
		return tipoCuentaService.filtraCuentas(id, estado);
	}
	
	@PostMapping("/nuevo")
	public TipoCuenta createTipoCuenta(@RequestBody TipoCuenta tipoCuenta) {
		return tipoCuentaRepository.save(tipoCuenta);
	}
	@PutMapping("/{id}")
	public TipoCuenta updateTipoCuenta(@RequestBody TipoCuenta tipoCuenta, @PathVariable Long id) {
		return tipoCuentaRepository.findById(id).map(tipo ->{
			tipo.setNombre(tipoCuenta.getNombre());
			return tipoCuentaRepository.save(tipo);
		}).orElseGet(()->{
			tipoCuenta.setId(id);
			return tipoCuentaRepository.save(tipoCuenta);
		});
	}
	
	
	
    @DeleteMapping("/{id}")
    public ResponseEntity<TipoCuenta> deleteTipoCuenta(@PathVariable Long id) {
        tipoCuentaService.delete(id);
        return ResponseEntity.ok().build();
    }
	
}
