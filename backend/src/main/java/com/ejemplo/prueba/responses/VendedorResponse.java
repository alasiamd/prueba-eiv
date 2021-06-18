package com.ejemplo.prueba.responses;

import java.time.LocalDate;

import com.ejemplo.prueba.entidades.Vendedor;


public class VendedorResponse {

	private Integer id;
    private String usuarioLogin;
	private String nombre;
	private Boolean habilitado;
	private LocalDate fechaNacimiento;
	private String observaciones;
    private LocalidadResponse localidad;

	
	public VendedorResponse( Vendedor v ) { 
		id = v.getId();
		usuarioLogin = v.getUsuarioLogin();
		nombre = v.getNombre();
		habilitado = v.getHabilitado();
		fechaNacimiento = v.getFechaNacimiento();
		observaciones = v.getObservaciones();
		localidad = new LocalidadResponse( v.getLocalidad());
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsuarioLogin() {
		return usuarioLogin;
	}


	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Boolean getHabilitado() {
		return habilitado;
	}


	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public LocalidadResponse getLocalidad() {
		return localidad;
	}


	public void setLocalidad(LocalidadResponse localidad) {
		this.localidad = localidad;
	}
	

}
