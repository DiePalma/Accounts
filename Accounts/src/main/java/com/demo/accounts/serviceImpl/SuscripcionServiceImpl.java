package com.demo.accounts.serviceImpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.accounts.model.Suscripcion;
import com.demo.accounts.repository.SuscripcionRepository;
import com.demo.accounts.service.SuscripcionService;

@Service
public class SuscripcionServiceImpl implements SuscripcionService{

	@Autowired
	private SuscripcionRepository suscripcionRepository;
	
	@Override
	public int updateEstado(Long suscripcion_id, String estado) {
		return suscripcionRepository.updateEstado(suscripcion_id, estado);
	}

	@Override
	public Page<Suscripcion> listSuscripciones(Pageable pageable) {
		return (Page<Suscripcion>) suscripcionRepository.findAll(pageable);
	}
	
	
}
