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

import com.demo.accounts.service.CuentaService;
@CrossOrigin
@RestController
@RequestMapping("/cuenta")
public class CuentaController {
	

	
	@Autowired
	private CuentaService cuentaService;
	
	public CuentaController (CuentaService cuentaService) {
		this.cuentaService=cuentaService;
	}
	
	
	@GetMapping("")
	public ResponseEntity<Page<Cuenta>> getCuentas(Pageable pageable){
		Page<Cuenta> cuentas = cuentaService.listCuentas(pageable);
		if (cuentas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cuentas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Cuenta>> getCuenta(@PathVariable Long id) {
		Optional<Cuenta> cuenta= cuentaService.findById(id);
			if (cuenta!=null) {
				return ResponseEntity.ok(cuenta);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
	}
	@PostMapping("/nueva")
	public ResponseEntity<Cuenta> createCuenta(@RequestBody Cuenta cuenta) {
		cuentaService.save(cuenta);
		return new ResponseEntity<Cuenta>(cuenta, HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Cuenta> updateCuenta(@RequestBody Cuenta cuentaUpdated, @PathVariable Long id) {
		return cuentaService.findById(id)
				.map(cuenta ->{
			cuenta.setCorreo(cuentaUpdated.getCorreo());
		
			 cuentaService.save(cuenta);
			 return ResponseEntity.ok(cuenta);
		}).orElseGet(()->{
			cuentaUpdated.setId(id);
			
			cuentaService.save(cuentaUpdated);
			return new ResponseEntity<Cuenta>(cuentaUpdated, HttpStatus.CREATED);
		});
	}
	

	
    @DeleteMapping("/{id}")
    public ResponseEntity<Cuenta> deleteCuenta(@PathVariable Long id) {
        cuentaService.delete(id);
        return ResponseEntity.ok().build();
    }
    
  
}