package com.quileia.spring.boot.backend.apirest.restaurant.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quileia.spring.boot.backend.apirest.restaurant.models.DAO.IMenuDAO;
import com.quileia.spring.boot.backend.apirest.restaurant.models.entity.Menu;

@Service
public class MenuServiceImp implements IMenuService {

	@Autowired
	private IMenuDAO menuDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Menu> findAll() {
		return (List<Menu>) menuDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Menu findById(Long id) {
		return menuDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Menu save(Menu menu) {
		return menuDAO.save(menu);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		menuDAO.deleteById(id);
		
	}
	
	
}
