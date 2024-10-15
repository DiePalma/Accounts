package com.demo.accounts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.demo.accounts.model.Cuenta;
import com.demo.accounts.model.Suscripcion;

public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long>{
	
	@Modifying
	@Transactional
	@Query("UPDATE Suscripcion s "
			+ "SET s.estado = :estado "
			+ "WHERE s.id = :suscripcion_id ")
	int updateEstado(@Param("suscripcion_id") Long suscripcion_id,
			@Param("estado") String estado);
	
	
	@Query("SELECT c "
			+ "FROM Cuenta c "
			+ "JOIN Suscripcion s ON s.cuenta.id = c.id "
			+ "JOIN TipoCuenta t ON s.tipoCuenta.id = :tipo_cuenta_id "
			+ "WHERE s.estado LIKE :estado")
	List<Cuenta> filtraCuentas(@Param("tipo_cuenta_id") Long tipo_cuenta_id, @Param("estado") String estado);
	
	
	
	
	
	
}

