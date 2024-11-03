package com.demo.accounts.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.demo.accounts.model.Suscripcion;

public interface SuscripcionService {

	
	public int updateEstado(Long suscripcion_id, String estado);

	public Page<Suscripcion> listSuscripciones(Pageable pageable);

	}
