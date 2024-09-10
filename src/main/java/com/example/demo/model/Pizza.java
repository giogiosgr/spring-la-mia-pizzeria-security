package com.example.demo.model;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.*;

@SuppressWarnings("unused")

@Entity
@Table(name = "pizzas")

public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// Viene usata la Bean Validation

	@NotNull
	@NotEmpty
	@Size(min=2, max=255)
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Size(max=500)
	@Column(length = 500)
	private String description;

	@Size(max=1000)
	@Column(length = 1000)
	private String photoUrl;

	@NotNull
	@Min(2)
	@Column(name = "price", nullable = false)
	private double price;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	//@Transient
	//private DecimalFormat formatter = new DecimalFormat("#,##0.00");

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String url) {
		this.photoUrl = url;
	}

	public double getPrice() {
		//return formatter.format(this.price) + '€';
		//return String.format("%.2f€", this.price);
		return price;
	}
	
	public String getFormattedPrice() {
		//return formatter.format(this.price) + '€';
		return String.format("%.2f €", this.price);
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
