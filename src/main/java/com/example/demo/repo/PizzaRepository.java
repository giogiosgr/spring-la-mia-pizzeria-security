package com.example.demo.repo;

import com.example.demo.model.Pizza;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

	List<Pizza> findByNameContainingOrderByName(String name);
	
	}
