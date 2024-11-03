package com.demo.accounts.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.accounts.model.Suscripcion;
import com.demo.accounts.repository.SuscripcionRepository;
import com.demo.accounts.service.SuscripcionService;

@CrossOrigin
@RestController
@RequestMapping("/suscripcion")
public class SuscripcionController {

	@Autowired
	private SuscripcionRepository suscripcionRepository;

	@Autowired
	private SuscripcionService suscripcionService;

	@GetMapping("")
	public ResponseEntity<Page<Suscripcion>> getSuscripciones(Pageable pageable) {
		Page<Suscripcion> suscripciones = suscripcionService.listSuscripciones(pageable);
		if (suscripciones.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(suscripciones);
	}

	@PostMapping("/nueva")
	public ResponseEntity <Suscripcion> createSuscripcion(@RequestBody Suscripcion suscripcion) {
		System.out.println(suscripcion.getCuenta());
		System.out.println(suscripcion.getTipoCuenta());
		 suscripcionRepository.save(suscripcion);
		 return new ResponseEntity<Suscripcion>(HttpStatus.CREATED);
	}

	@PutMapping("/actualizarEstado/{suscripcion_id}/{estado}")
	public ResponseEntity<Integer> updateSubscription(@PathVariable Long suscripcion_id, @PathVariable String estado) {

		suscripcionService.updateEstado(suscripcion_id, estado);
		return new ResponseEntity<Integer>(HttpStatus.CREATED);
	}
}
