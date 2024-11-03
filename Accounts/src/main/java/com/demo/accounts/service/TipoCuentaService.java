package com.demo.accounts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.accounts.model.Cuenta;
import com.demo.accounts.model.TipoCuenta;



public interface TipoCuentaService {

	public Page<TipoCuenta>listTiposCuenta(Pageable pageable);
	
	public void save(TipoCuenta tipoCuenta);
	
	public void delete(Long id);
	
	public Optional<TipoCuenta> findById(Long id);

	//Prueba con ResponseEntity y Page
	public Page<Cuenta> filtraCuentas(Long tipo_cuenta_id, String estado, Pageable pageable);

	/* Codigo antiguo:
	List<Cuenta> filtraCuentas(Long tipo_cuenta_id, String estado);
	*/
;}
