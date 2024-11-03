package com.demo.accounts.service;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.accounts.model.Cuenta;

public interface CuentaService {

	public Page<Cuenta>listCuentas(Pageable pageable);
	
	public void save(Cuenta cuenta);
	
	public void delete(Long id);
	
	public Optional<Cuenta> findById(Long id);
	
	//public Cuenta subscribe(Long cuentaId, Long tipoCuentaId);
	
	
;}
