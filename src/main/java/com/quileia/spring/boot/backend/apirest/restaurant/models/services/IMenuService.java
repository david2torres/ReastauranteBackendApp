package com.quileia.spring.boot.backend.apirest.restaurant.models.services;

import java.util.List;
import com.quileia.spring.boot.backend.apirest.restaurant.models.entity.Menu;

public interface IMenuService {
	
	public List<Menu> findAll();

	public Menu findById(Long id);
	
	public Menu save(Menu menu);
	
	public void delete(Long id);
}
