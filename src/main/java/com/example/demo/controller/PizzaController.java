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

import java.util.List;
import java.util.Optional;
import com.example.demo.model.Pizza;
import com.example.demo.model.Discount;
import com.example.demo.repo.DiscountRepository;
import com.example.demo.repo.PizzaRepository;
import com.example.demo.service.PizzaService;

import jakarta.validation.Valid;

@SuppressWarnings("unused")

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	PizzaService pizzaService;

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

		return "/pizzas/show";
	}

	@GetMapping("/search")
	public String pizzaSearch(@RequestParam String name, Model model) {

		// consegna al model di specifiche ennuple di pizza tramite JPA Query Methods
		model.addAttribute("pizzas", pizzaService.getByNameWithOrderByName(name));

		return "/pizzas/index";
	}

	// CREATE
	@GetMapping("/create")
	public String pizzaCreate(Model model) {

		model.addAttribute("pizza", new Pizza());

		return "/pizzas/create";
	}

	// STORE
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
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

		return "/pizzas/edit";
	}

	// UPDATE
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
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

		Discount discount = new Discount();

		Pizza pizza = pizzaService.getById(1);

		discount.setName("scontone");

		discount.setPizza(pizza);

		repoD.save(discount);

		for (Discount x : pizza.getDiscounts())
			System.out.println(x.getName());

		return "/pages/test";
	}

}
