package com.demo.accounts.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
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
	public List<TipoCuenta> listCuentas() {
		return (List<TipoCuenta>) tipoCuentaRepository.findAll();
		
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
	public TipoCuenta findTipoCuenta(TipoCuenta tipoCuenta) {
		return tipoCuentaRepository.findById(tipoCuenta.getId()).orElse(null);
		
	}
	

	@Override
	public List<Cuenta> filtraCuentas(Long tipo_cuenta_id, String estado) {
		return suscripcionRepository.filtraCuentas(tipo_cuenta_id, estado);
	}
}
