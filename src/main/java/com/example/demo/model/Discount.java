package com.example.demo.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@SuppressWarnings("unused")

@Entity
@Table(name = "discounts")

public class Discount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// Viene usata la Bean Validation

	@NotNull
	@NotEmpty
	@Size(min = 2, max = 255)
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@NotNull
	private LocalDateTime offerStart;

	@NotNull
	@Future
	private LocalDateTime offerEnd;

	@ManyToOne
	@JoinColumn(name = "pizza_id", nullable = false)
	private Pizza pizza;

	// getters e setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getOfferStart() {
		return offerStart;
	}

	public void setOfferStart(LocalDateTime offerStart) {
		this.offerStart = offerStart;
	}

	public LocalDateTime getOfferEnd() {
		return offerEnd;
	}

	public void setOfferEnd(LocalDateTime offerEnd) {
		this.offerEnd = offerEnd;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

}
