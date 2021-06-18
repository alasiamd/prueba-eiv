package com.ejemplo.prueba.ctrl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejemplo.prueba.entidades.Localidad;
import com.ejemplo.prueba.entidades.Vendedor;
import com.ejemplo.prueba.repos.LocalidadRepository;
import com.ejemplo.prueba.repos.VendedorRepository;
import com.ejemplo.prueba.requests.VendedorActualizarRequest;
import com.ejemplo.prueba.requests.VendedorAgregarRequest;
import com.ejemplo.prueba.responses.VendedorResponse;



@RestController
@RequestMapping(path = "/api/vendedores")

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})

public class VendedorCtrl {

	@Autowired
	LocalidadRepository localidadRepository;

	@Autowired
	VendedorRepository vendedorRepository;

	@GetMapping(path = "/todos", produces = "application/json")
	@Transactional
	public ResponseEntity<List<VendedorResponse>> buscarVendedores() {

		List<Vendedor> vendedores = vendedorRepository.findAll();

		List<VendedorResponse> respuesta = vendedores.stream().map(v -> new VendedorResponse(v))
				.collect(Collectors.toList());

		return ResponseEntity.ok(respuesta);

	}

	@GetMapping(path = "/{id}/foto", produces = "application/octet-stream")
	@Transactional
	public ResponseEntity<byte[]> leerFotoVendedor(@PathVariable("id") Integer vendedorId) throws IOException {

		Optional<Vendedor> optVendedor = vendedorRepository.findById(vendedorId);

		if (!optVendedor.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Vendedor v = optVendedor.get();

		byte[] contenido = v.getFoto();

		if (contenido == null || contenido.length == 0) {
			return ResponseEntity.noContent().build();
		}

		String contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(contenido));

		MediaType mediaType = MediaType.parseMediaType(contentType);

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(mediaType);
		headers.setContentDispositionFormData("inline", String.format("vendedor-foto-%s.png", vendedorId));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(contenido.length);
		headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);

		return ResponseEntity.ok().headers(headers).body(contenido);

	}

	@PostMapping(path = "/{id}/foto")
	@Transactional
	public ResponseEntity<?> subirFotoVendedor(@PathVariable("id") Integer vendedorId,
			@RequestParam("file") MultipartFile fila) throws IOException {

		Optional<Vendedor> optVendedor = vendedorRepository.findById(vendedorId);

		if (!optVendedor.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Vendedor v = optVendedor.get();

		try {
			v.setFoto(fila.getBytes());
		} catch (IOException e) {

			return ResponseEntity.unprocessableEntity().build();
		}

		vendedorRepository.save(v);

		return ResponseEntity.ok().build();
	}

	@PostMapping()
	@Transactional
	public ResponseEntity<VendedorResponse> agregarVendedor(@RequestBody VendedorAgregarRequest request) {

		Optional<Localidad> optLocalidad = localidadRepository.findById(request.getLocalidadId());

		if (!optLocalidad.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Localidad l = optLocalidad.get();

		Vendedor nuevo = new Vendedor();
		nuevo.setUsuarioLogin(request.getUsuarioLogin());
		nuevo.setNombre(request.getNombre());
		nuevo.setHabilitado(request.getHabilitado());
		nuevo.setFechaNacimiento(request.getFechaNacimiento());
		nuevo.setObservaciones(request.getObservaciones());
		nuevo.setLocalidad(l);

		try {
			Vendedor entidad = vendedorRepository.save(nuevo);

			return ResponseEntity.ok(new VendedorResponse(entidad));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@PutMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<VendedorResponse> actualizarVendedor(@PathVariable("id") Integer vendedorId,
			@RequestBody VendedorActualizarRequest request) {

		Optional<Vendedor> optVendedor = vendedorRepository.findById(vendedorId);

		if (!optVendedor.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Vendedor v = optVendedor.get();

		Optional<Localidad> optLocalidad = localidadRepository.findById(request.getLocalidadId());

		if (!optLocalidad.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Localidad l = optLocalidad.get();

		v.setUsuarioLogin(request.getUsuarioLogin());
		v.setNombre(request.getNombre());
		v.setHabilitado(request.getHabilitado());
		v.setFechaNacimiento(request.getFechaNacimiento());
		v.setObservaciones(request.getObservaciones());
		v.setLocalidad(l);

		try {
			Vendedor entidad = vendedorRepository.save(v);

			return ResponseEntity.ok(new VendedorResponse(entidad));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@DeleteMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<?> borrarVendedor(@PathVariable("id") Integer vendedorId) {

		Optional<Vendedor> optVendedor = vendedorRepository.findById(vendedorId);

		if (!optVendedor.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		vendedorRepository.deleteById(vendedorId);

		return ResponseEntity.noContent().build();

	}

}
