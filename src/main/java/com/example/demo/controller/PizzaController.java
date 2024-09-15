package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.example.demo.model.Pizza;
import com.example.demo.model.Discount;
import com.example.demo.model.Ingredient;
import com.example.demo.repo.DiscountRepository;
import com.example.demo.repo.PizzaRepository;
import com.example.demo.service.IngredientService;
import com.example.demo.service.PizzaService;

import jakarta.validation.Valid;

@SuppressWarnings("unused")

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	PizzaService pizzaService;
	
	@Autowired
	IngredientService iService;

	@GetMapping
	public String index(Model model) {

		// consegna dei dati a pizzas/index
		model.addAttribute("pizzas", pizzaService.getAll());

		return "/pizzas/index";
	}

	@GetMapping("/show/{id}")
	public String pizzaDetails(@PathVariable int id, Model model) {

		// consegna al model di una specifica ennupla pizza tramite ID
		model.addAttribute("pizza", pizzaService.getById(id));
		// consegna di un localDateTime per confronto con le date di fine offerta e mostrare solo quelle valide
		model.addAttribute("localDateTime", LocalDateTime.now());
		
		model.addAttribute("ingredients", pizzaService.getById(id).getIngredients());

		return "/pizzas/show";
	}

	@GetMapping("/search")
	public String pizzaSearch(@RequestParam String name, Model model) {

		// consegna al model di specifiche ennuple di pizza tramite JPA Query Methods (tramite service)
		model.addAttribute("pizzas", pizzaService.getByNameWithOrderByName(name));

		return "/pizzas/index";
	}

	// CREATE
	@GetMapping("/create")
	public String pizzaCreate(Model model) {

		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredients", iService.getAll());

		return "/pizzas/create";
	}

	// STORE
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("ingredients", iService.getAll());
			return "/pizzas/create";
		}

		pizzaService.save(pizzaForm);

		attributes.addFlashAttribute("successMessage", "Pizza " + pizzaForm.getName() + " creata con successo");

		return "redirect:/pizzas";
	}

	// EDIT
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		model.addAttribute("pizza", pizzaService.getById(id));
		model.addAttribute("ingredients", iService.getAll());

		return "/pizzas/edit";
	}

	// UPDATE
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("ingredients", iService.getAll());
			return "/pizzas/edit";
		}

		pizzaService.save(pizzaForm);

		attributes.addFlashAttribute("successMessage", "Pizza " + pizzaForm.getName() + " modificata con successo");

		return "redirect:/pizzas";
	}

	// DELETE
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable int id, Model model, RedirectAttributes attributes) {

		Pizza deletedPizza = pizzaService.getById(id);

		pizzaService.deleteById(id);

		attributes.addFlashAttribute("successMessage", "Pizza " + deletedPizza.getName() + " eliminata con successo");

		return "redirect:/pizzas";
	}

	// test

	@Autowired
	private DiscountRepository repoD;

	@GetMapping("/test")
	public String test(Model model) {

		Pizza pizza = pizzaService.getById(1);

		/*
		 
		Discount discount = new Discount();  
		 
		discount.setName("scontone");

		discount.setPizza(pizza);

		repoD.save(discount);

		for (Discount x : pizza.getDiscounts())
			System.out.println(x.getName());
			
		*/
		
		List<Ingredient> test = pizza.getIngredients();
		
		System.out.println(test.get(0).getName());

		return "/pages/test";
	}

}
