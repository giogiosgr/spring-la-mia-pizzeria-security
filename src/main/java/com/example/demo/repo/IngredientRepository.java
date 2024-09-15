package com.example.demo.repo;

import com.example.demo.model.Ingredient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

	List<Ingredient> findByNameContainingOrderByName(String name);
	
	}
