package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pizza;
import com.example.demo.service.PizzaService;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class PizzaRestController {
	
	@Autowired
	PizzaService pizzaService;
	
	@GetMapping()
	public List<Pizza> index(@RequestParam(required=false) String word) {
		
		List<Pizza> result;
		
		if(word != null && !word.isEmpty()) {
			result = pizzaService.getByNameWithOrderByName(word);
		} else {
			result = pizzaService.getAll();
		}
			
		return result;
	}
	
}
