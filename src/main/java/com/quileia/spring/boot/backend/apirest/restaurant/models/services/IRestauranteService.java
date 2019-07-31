package com.quileia.spring.boot.backend.apirest.restaurant.models.services;

import java.util.List;
import com.quileia.spring.boot.backend.apirest.restaurant.models.entity.Restaurante;

public interface IRestauranteService {
	
	public List<Restaurante> findAll();
	
	public Restaurante findById(Long id);
	
	public Restaurante save(Restaurante restaurante);
	
	public void  delete(Long id);

}
