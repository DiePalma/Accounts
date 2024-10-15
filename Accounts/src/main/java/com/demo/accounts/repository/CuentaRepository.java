package com.demo.accounts.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.accounts.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	
	

}
