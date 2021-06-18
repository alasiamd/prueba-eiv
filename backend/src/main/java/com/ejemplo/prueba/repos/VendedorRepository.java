package com.ejemplo.prueba.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.prueba.entidades.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
	
}


