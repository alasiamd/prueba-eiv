package com.ejemplo.prueba.ctrl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejemplo.prueba.entidades.Localidad;
import com.ejemplo.prueba.repos.LocalidadRepository;
import com.ejemplo.prueba.responses.LocalidadResponse;

@RestController
@RequestMapping(path = "/api/localidades")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class LocalidadCtrl {

	@Autowired
	LocalidadRepository localidadRepository;

	@GetMapping(path = "/todas", produces = "application/json")
	public ResponseEntity<List<LocalidadResponse>> buscarLocalidades() {

		List<Localidad> localidades = localidadRepository.findAll();

		List<LocalidadResponse> respuesta = localidades.stream().map(l -> new LocalidadResponse(l))
				.collect(Collectors.toList());

		return ResponseEntity.ok(respuesta);

	}

}
