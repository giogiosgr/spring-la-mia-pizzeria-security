package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Discount;
import com.example.demo.repo.DiscountRepository;

import jakarta.validation.Valid;

public class DiscountService {
	
	// repository field con autowired per dependency injection
	@Autowired
	private DiscountRepository repo;
		
	
    public Discount getById(Integer id) {
		
		return repo.findById(id).get();
		
	}

	public List<Discount> getAll() {
		
		return repo.findAll();
		
	}

	public void save(@Valid Discount discountForm) {
		
		repo.save(discountForm);
		
	}

	public void deleteById(int id) {
		
		repo.deleteById(id);
		
	}

}
