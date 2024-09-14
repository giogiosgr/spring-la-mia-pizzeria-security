package com.example.demo.service;

import java.util.List;

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

	public List<Pizza> getAll() {

		return repo.findAll();

	}

	public Object getByNameWithOrderByName(String name) {

		return repo.findByNameContainingOrderByName(name);

	}

	public void save(@Valid Pizza pizzaForm) {

		repo.save(pizzaForm);

	}

	public void deleteById(int id) {

		repo.deleteById(id);

	}

}
