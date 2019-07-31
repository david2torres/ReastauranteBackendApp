package com.quileia.spring.boot.backend.apirest.restaurant.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurantes")
public class Restaurante implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6661473467286936395L;

	/**
	 * Atributos de un Restaurante
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String razonSocial;
	@Column(nullable = false)
	private String nombreComercial;
	@Column(nullable = false)
	private int tipoRestaurante;
	
	private String tRestaurante;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getTipoRestaurante() {
		
		switch (tipoRestaurante) {
		case 1:
			tRestaurante = "Vegano";
			break;
		case 2:
			tRestaurante = "Vegetariano";
			break;
		case 3:
			tRestaurante = "Carnes Rojas y Aves";
			break;
		case 4:
			tRestaurante = "Pescados y Mariscos";
			break;
		default:
			break;
		}
		return tRestaurante;
	}

	public void setTipoRestaurante(String tRestaurante	) {
		
		switch (tRestaurante) {
		case "Vegano":
			tipoRestaurante = 1;
			break;
		case "Vegetariano":
			tipoRestaurante = 2;
			break;
		case "Carnes Rojas y Aves":
			tipoRestaurante = 3;
			break;
		case "Pescados y Mariscos":
			tipoRestaurante = 4;
			break;
		default:
			break;
		}
		this.tipoRestaurante = tipoRestaurante;
	}

	@Override
	public String toString() {
		return "Restautrante [id=" + id + ", razonSocial=" + razonSocial + ", nombreComercial=" + nombreComercial
				+ ", tipoRestaurante=" + tipoRestaurante + "]";
	}
}
