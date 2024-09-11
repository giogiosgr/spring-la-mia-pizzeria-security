package com.example.demo.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	@Size(min=2, max=255)
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	private LocalDateTime offerStart;
	
	private LocalDateTime offerEnd;
	
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
	
}
