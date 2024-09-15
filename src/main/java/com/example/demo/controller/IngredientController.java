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

import com.example.demo.model.Ingredient;
import com.example.demo.service.IngredientService;

import jakarta.validation.Valid;

@SuppressWarnings("unused")

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
	
	@Autowired
	IngredientService iService;
	
	@GetMapping
	public String index(Model model) {

		// consegna dei dati a ingredients/index
		model.addAttribute("ingredients", iService.getAll());

		return "/ingredients/index";
	}

	// CREATE
	@GetMapping("/create")
	public String pizzaCreate(Model model) {

		model.addAttribute("ingredient", new Ingredient());

		return "/ingredients/create";
	}

	// STORE
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("ingredient") Ingredient ingredientForm, BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return "/ingredients/create";
		}

		iService.save(ingredientForm);

		attributes.addFlashAttribute("successMessage", "Ingrediente " + ingredientForm.getName() + " creato con successo");

		return "redirect:/ingredients";
	}

	// EDIT
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		model.addAttribute("ingredient", iService.getById(id));

		return "/ingredients/edit";
	}

	// UPDATE
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("ingredient") Ingredient ingredientForm, BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return "/ingredients/edit";
		}

		iService.save(ingredientForm);

		attributes.addFlashAttribute("successMessage", "Ingrediente " + ingredientForm.getName() + " modificato con successo");

		return "redirect:/ingredients";
	}

	// DELETE
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable int id, Model model, RedirectAttributes attributes) {

		Ingredient deletedIngredient = iService.getById(id);

		iService.deleteById(id);

		attributes.addFlashAttribute("successMessage", "Ingrediente " + deletedIngredient.getName() + " eliminata con successo");

		return "redirect:/ingredients";
	}
	
}