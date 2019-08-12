package com.quileia.spring.boot.backend.apirest.restaurant.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menus")
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2961767647380805567L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private int tipoMenu;
	@Column(nullable = false)
	private String nombreMenu;
	@Column(nullable = false)
	private String Ingredientes;
	@Column(nullable = false)
	private int precio;
	@Column(nullable = false)
	private String dia;

	@Column(nullable = false)
	private String descuento;
	
	
    public String getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	private String tmenu;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipoMenu() {

		switch (tipoMenu) {
		case 1:
			tmenu = "Entrada";
			break;
		case 2:
			tmenu = "Plato Fuerte";
			break;
		case 3:
			tmenu = "Postres";
			break;
		case 4:
			tmenu = "Bebidas";
			break;
		default:
			break;
		}
		return tmenu;
	}

	public void setTipoMenu(String tmenu) {
		
		switch (tmenu) {
		case "Entrada":
			tipoMenu = 1;
			break;
		case "Plato Fuerte":
			tipoMenu = 2;
			break;
		case "Postres":
			tipoMenu = 3;
			break;
		case "Bebidas":
			tipoMenu = 4;
			break;
		default:
			break;
		}
		this.tipoMenu = tipoMenu;
	}

	public String getNombreMenu() {
		return nombreMenu;
	}

	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}

	public String getIngredientes() {
		return Ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		Ingredientes = ingredientes;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

}
