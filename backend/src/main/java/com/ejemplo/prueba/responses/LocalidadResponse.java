package com.ejemplo.prueba.responses;

import com.ejemplo.prueba.entidades.Localidad;

public class LocalidadResponse {

	private Integer id;

    private String localidad;

	private String codigoPostal;
	
	public LocalidadResponse( Localidad l ) { 
		id = l.getId();
		localidad = l.getLocalidad();
		codigoPostal = l.getCodigoPostal();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	

}
