package com.example.demo.repo;

import com.example.demo.model.Discount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
	
	}
