package com.quileia.spring.boot.backend.apirest.restaurant.models.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.quileia.spring.boot.backend.apirest.restaurant.models.entity.Restaurante;
import com.quileia.spring.boot.backend.apirest.restaurant.models.services.IRestauranteService;

@CrossOrigin(origins = {"http://localhost:4200"} )
@RestController
@RequestMapping("/api")
public class RestauranteRestController {
	
	@Autowired
	private IRestauranteService restauranteService;
	
	//SHOW ALL
	@GetMapping("/restaurantes")
	public List<Restaurante> index(){
		return restauranteService.findAll();
	}
	
	//READ
	@GetMapping("/restaurantes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Restaurante restaurante = null;
		
		Map<String, Object> response = new HashMap<>();
		try {
			restaurante= restauranteService.findById(id);
		} catch(DataAccessException e){
			response.put( "mensaje", "Error en la Consulta");
			response.put( "mensaje", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
			
		}
		if(restaurante == null) {
			response.put( "mensaje", "El restaurante con el Id: ".concat(id.toString().concat(" No extiste en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<Restaurante>(restaurante, HttpStatus.OK);
	}
	
	//CREATED
	@PostMapping("/restaurantes")
	public ResponseEntity<?>  created(@RequestBody Restaurante restaurante) {
		Restaurante restauranNew = null;
		
		Map<String, Object> response = new HashMap<>();
		try {
			restauranNew = restauranteService.save(restaurante);
		}catch(DataAccessException e) {
			response.put( "mensaje", "Error al insertar en Base de Datos");
			response.put( "mensaje", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		response.put("mensaje", "Restaurante Creado con Exito");
		response.put("restaurante", restauranNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//UPDATE
	@PutMapping("/restaurantes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Restaurante restaurante, @PathVariable Long id) {
		Restaurante restauranteActual = restauranteService.findById(id);
		Restaurante restauranteUpdate=null;
		Map<String, Object> response = new HashMap<>();
		
		if(restauranteActual == null) {
			response.put( "mensaje", "Error: No se puede editar: ".concat(id.toString().concat(" No extiste en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);	
		}
		try {
		//Asignacion de Nuevos Parametros
		restauranteActual.setNombreComercial(restaurante.getNombreComercial());
		restauranteActual.setRazonSocial(restaurante.getRazonSocial());
		restauranteActual.setTipoRestaurante(restaurante.getTipoRestaurante());
		restauranteUpdate = restauranteService.save(restauranteActual);
		
		}catch(DataAccessException e) {
			response.put( "mensaje", "Error al actualizar en Base de Datos");
			response.put( "mensaje", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		response.put("mensaje", "Restaurante Editado con Exito");
		response.put("restaurante", restauranteUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	//DELETED
	@DeleteMapping("/restaurantes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
		restauranteService.delete(id);
		}catch(DataAccessException e) {
			response.put( "mensaje", "Error al eliminar en Base de Datos");
			response.put( "mensaje", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		response.put("mensaje", "Menu Eliminado con Exito");
		//response.put("menu", menuUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}	
}
