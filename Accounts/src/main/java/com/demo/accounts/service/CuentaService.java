package com.demo.accounts.service;

import java.util.List;

import com.demo.accounts.model.Cuenta;

public interface CuentaService {

	public List<Cuenta>listCuentas();
	
	public void save(Cuenta cuenta);
	
	public void delete(Cuenta cuenta);
	
	public Cuenta findCuenta(Cuenta cuenta);
	
	//public Cuenta subscribe(Long cuentaId, Long tipoCuentaId);
	
	
;}
