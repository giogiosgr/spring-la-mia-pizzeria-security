package com.example.demo.repo;

import com.example.demo.model.PizzaMaker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaMakerRepository extends JpaRepository<PizzaMaker, Integer> {

}
