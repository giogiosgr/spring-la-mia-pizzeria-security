package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ingredient;
import com.example.demo.repo.IngredientRepository;

import jakarta.validation.Valid;

@Service
public class IngredientService {

	// repository field con autowired per dependency injection
	@Autowired
	private IngredientRepository repo;

	public Ingredient getById(Integer id) {

		return repo.findById(id).get();

	}

	public List<Ingredient> getAll() {

		return repo.findAll();

	}

	public void save(@Valid Ingredient ingredientForm) {

		repo.save(ingredientForm);

	}

	public void deleteById(int id) {

		repo.deleteById(id);

	}

}
