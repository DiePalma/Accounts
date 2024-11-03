package com.demo.accounts.serviceImpl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<Cuenta> listCuentas(Pageable pageable) {
		return (Page<Cuenta>) cuentaRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Cuenta cuenta) {
		cuentaRepository.save(cuenta);
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		cuentaRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Cuenta> findById(Long id) {
		return cuentaRepository.findById(id);
	}

	

	
	
	
}
