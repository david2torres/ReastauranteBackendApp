package com.quileia.spring.boot.backend.apirest.restaurant.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quileia.spring.boot.backend.apirest.restaurant.models.DAO.IRestauranteDAO;
import com.quileia.spring.boot.backend.apirest.restaurant.models.entity.Restaurante;

@Service
public class RestauranteServiceImp implements IRestauranteService {

	@Autowired
	private IRestauranteDAO restauranteDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Restaurante> findAll() {
		return (List<Restaurante>) restauranteDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Restaurante findById(Long id) {
		return restauranteDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Restaurante save(Restaurante restaurante) {
		return restauranteDAO.save(restaurante);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		restauranteDAO.deleteById(id);
	}
}
