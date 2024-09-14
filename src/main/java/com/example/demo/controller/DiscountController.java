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
import com.example.demo.repo.DiscountRepository;
import com.example.demo.repo.PizzaRepository;
import com.example.demo.service.DiscountService;

import jakarta.validation.Valid;

@SuppressWarnings("unused")

@Controller
@RequestMapping("/discounts")

public class DiscountController {

	@Autowired
	DiscountService dService;

	// CREATE
	@GetMapping("/create")
	public String create(Model model) {

		model.addAttribute("discount", new Discount());

		return "/discounts/create";
	}

	// STORE
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("discount") Discount discountForm, BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return "/pizzas/create";
		}

		dService.save(discountForm);

		attributes.addFlashAttribute("successMessage", "Offerta " + discountForm.getName() + " creata con successo");

		return "redirect:/pizzas";
	}

	// EDIT
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		model.addAttribute("discount", dService.getById(id));

		return "/discounts/edit";
	}

	// UPDATE
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("discount") Discount discountForm, BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return "/pizzas/edit";
		}

		dService.save(discountForm);

		attributes.addFlashAttribute("successMessage", "Offerta " + discountForm.getName() + " modificata con successo");

		return "redirect:/pizzas";
	}

}
