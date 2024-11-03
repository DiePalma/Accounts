package com.demo.accounts.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.accounts.model.Cuenta;
import com.demo.accounts.model.TipoCuenta;
import com.demo.accounts.repository.SuscripcionRepository;
import com.demo.accounts.repository.TipoCuentaRepository;
import com.demo.accounts.service.TipoCuentaService;

@Service
public class TipoCuentaServiceImpl implements TipoCuentaService{

	@Autowired
	private TipoCuentaRepository tipoCuentaRepository;
	

	
	@Autowired
	private SuscripcionRepository suscripcionRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Page<TipoCuenta> listTiposCuenta(Pageable pageable) {
		return (Page<TipoCuenta>) tipoCuentaRepository.findAll(pageable);
		
	}
	

	@Override
	@Transactional
	public void save(TipoCuenta tipoCuenta) {
		// TODO Auto-generated method stub
		tipoCuentaRepository.save(tipoCuenta);
		
	}

	@Override
	@Transactional
	@Modifying
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
		tipoCuentaRepository.deleteById(id);
	
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<TipoCuenta> findById(Long id) {
		return tipoCuentaRepository.findById(id);
		
	}
	


	@Override
	public Page<Cuenta> filtraCuentas(Long tipo_cuenta_id, String estado, Pageable pageable) {
		return suscripcionRepository.filtraCuentas(tipo_cuenta_id, estado, pageable);
	}
	/*
	@Override
	public List<Cuenta> filtraCuentas(Long tipo_cuenta_id, String estado) {
		return suscripcionRepository.filtraCuentas(tipo_cuenta_id, estado);
	}
	*/
}
