package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pizza;
import com.example.demo.repo.PizzaRepository;

import jakarta.validation.Valid;

@Service
public class PizzaService {

	// repository field con autowired per dependency injection
	@Autowired
	private PizzaRepository repo;

	public Pizza getById(Integer id) {

		return repo.findById(id).get();

	}
	
	public Optional<Pizza> getOptionalById(Integer id) {

		return repo.findById(id);

	}

	public List<Pizza> getAll() {

		return repo.findAll();

	}

	public List<Pizza> getByNameWithOrderByName(String name) {

		return repo.findByNameContainingOrderByName(name);

	}

	public void save(@Valid Pizza pizzaForm) {

		repo.save(pizzaForm);

	}
	
	public Pizza create(Pizza pizza) {
		
		return repo.save(pizza);
		
	}
	
    public Pizza update(Pizza pizza) {
		
		pizza.setUpdatedAt(LocalDateTime.now());
		return repo.save(pizza);
		
	}
	
	public void deleteById(int id) {

		repo.deleteById(id);

	}

}
