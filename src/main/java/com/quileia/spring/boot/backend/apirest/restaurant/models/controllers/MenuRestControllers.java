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
import com.quileia.spring.boot.backend.apirest.restaurant.models.entity.Menu;
import com.quileia.spring.boot.backend.apirest.restaurant.models.services.IMenuService;

@CrossOrigin(origins = {"http://localhost:4200" } )
@RestController
@RequestMapping("/api")
public class MenuRestControllers {

	@Autowired
	private IMenuService menuService;
	
	//SHOW ALL
	@GetMapping("/menus")
	public List<Menu> index(){
		return menuService.findAll();
	}
	
	//READ
	@GetMapping("/menus/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Menu menu= null;
		
		Map<String, Object> response = new HashMap<>();
		try {
			menu= menuService.findById(id);
		} catch(DataAccessException e){
			response.put( "mensaje", "Error en la Consulta");
			response.put( "mensaje", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);	
			
		}
		if(menu == null) {
			response.put( "mensaje", "El menu con el Id: ".concat(id.toString().concat(" No extiste en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<Menu>(menu, HttpStatus.OK);
	}
	
	//CREATED
	@PostMapping("/menus")
	public ResponseEntity<?>  create(@RequestBody Menu menu) {
		Menu menuNew= null;
		
		Map<String, Object> response = new HashMap<>();
		try {
			menuNew= menuService.save(menu);
		} catch(DataAccessException e){
			response.put( "mensaje", "Error al insertar en Base de Datos");
			response.put( "mensaje", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		response.put("mensaje", "Menu Creado con Exito");
		response.put("menu", menuNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//UPDATE
	@PutMapping("/menus/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>  update(@RequestBody Menu menu, @PathVariable Long id) {
		Menu menuActual = menuService.findById(id);
		Menu menuUpdate=null;
		Map<String, Object> response = new HashMap<>();
		
		if(menuActual == null) {
			response.put( "mensaje", "Error: No se puede editar: ".concat(id.toString().concat(" No extiste en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);	
		}
		
		try {
		//Asignacion de Nuevos Parametros
		menuActual.setNombreMenu(menu.getNombreMenu());
		menuActual.setIngredientes(menu.getIngredientes());
		menuActual.setTipoMenu(menu.getTipoMenu());
		menuActual.setPrecio(menu.getPrecio());
		menuUpdate = menuService.save(menuActual);
		}catch(DataAccessException e){
			response.put( "mensaje", "Error al actualizar en Base de Datos");
			response.put( "mensaje", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		response.put("mensaje", "Menu Editado con Exito");
		response.put("menu", menuUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	//DELETED
	@DeleteMapping("/menus/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			menuService.delete(id);
		}catch(DataAccessException e){
			response.put( "mensaje", "Error al eliminar en Base de Datos");
			response.put( "mensaje", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		response.put("mensaje", "Menu Eliminado con Exito");
		//response.put("menu", menuUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		
	}
	
}
