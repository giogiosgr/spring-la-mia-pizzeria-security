package com.example.demo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Pizza;
import com.example.demo.service.PizzaService;

import jakarta.validation.Valid;

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
	
	@GetMapping("/{id}")
	public ResponseEntity<Pizza> show(@PathVariable Integer id) {
		
		Optional<Pizza> pizza = pizzaService.getOptionalById(id);
		
		if (pizza.isPresent()) {
			return new ResponseEntity<>(pizza.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public Pizza store(@Valid @RequestBody Pizza pizza) {
		
		 return pizzaService.create(pizza);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pizza> update(@Valid @RequestBody Pizza pizza, @PathVariable Integer id) {
		
		Optional<Pizza> foundPizza = pizzaService.getOptionalById(id);
		
		if (foundPizza.isPresent()) {
			// l'id va caricato nel payload (nel Json)
			return new ResponseEntity<>(pizzaService.update(pizza), HttpStatus.OK);
		}
			
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pizza> delete(@PathVariable Integer id) {
		
		Optional<Pizza> pizza = pizzaService.getOptionalById(id);
		
		if (pizza.isPresent()) {
			pizzaService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
		
}


/* esempio di JSON
 
{
    "name": "capricciosa",
    "price": 9.00
}

*/

