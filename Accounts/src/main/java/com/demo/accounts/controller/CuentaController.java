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
import com.demo.accounts.repository.CuentaRepository;
@CrossOrigin
@RestController
@RequestMapping("/cuenta")
public class CuentaController {
	
	@Autowired
	private CuentaRepository cuentaRepository;
	

	
	public CuentaController (CuentaRepository cuentaRepository) {
		this.cuentaRepository= cuentaRepository;
	}
	
	
	@GetMapping("")
	public List<Cuenta> getCuentas(){
		return cuentaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Cuenta getCuenta(@PathVariable Long id) {
		return cuentaRepository.findById(id).orElseThrow(RuntimeException::new);
	}
	@PostMapping("/nueva")
	public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
		return cuentaRepository.save(cuenta);
	}
	@PutMapping("/{id}")
	public Cuenta updateCuenta(@RequestBody Cuenta cuenta, @PathVariable Long id) {
		return cuentaRepository.findById(id).map(tipo ->{
			tipo.setCorreo(cuenta.getCorreo());
		
			return cuentaRepository.save(tipo);
		}).orElseGet(()->{
			cuenta.setId(id);
			return cuentaRepository.save(cuenta);
		});
	}
	

	
    @DeleteMapping("/{id}")
    public ResponseEntity<Cuenta> deleteCuenta(@PathVariable Long id) {
        cuentaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
  
}