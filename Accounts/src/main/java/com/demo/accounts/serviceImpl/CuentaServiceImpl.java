package com.demo.accounts.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.accounts.model.Cuenta;
import com.demo.accounts.repository.CuentaRepository;
import com.demo.accounts.service.CuentaService;

@Service
public class CuentaServiceImpl implements CuentaService{

	@Autowired
	private CuentaRepository cuentaRepository;
	


	@Override
	@Transactional(readOnly = true)
	public List<Cuenta> listCuentas() {
		return (List<Cuenta>) cuentaRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Cuenta cuenta) {
		cuentaRepository.save(cuenta);
		
	}

	@Override
	@Transactional
	public void delete(Cuenta cuenta) {
		// TODO Auto-generated method stub
		cuentaRepository.delete(cuenta);
	}

	@Override
	@Transactional(readOnly=true)
	public Cuenta findCuenta(Cuenta cuenta) {
		return cuentaRepository.findById(cuenta.getId()).orElse(null);
	}

	

	
	
	
}
