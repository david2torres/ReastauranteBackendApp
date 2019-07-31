package com.quileia.spring.boot.backend.apirest.restaurant.models.DAO;

import org.springframework.data.repository.CrudRepository;
import com.quileia.spring.boot.backend.apirest.restaurant.models.entity.Restaurante;

public interface IRestauranteDAO extends CrudRepository<Restaurante, Long>{

}
