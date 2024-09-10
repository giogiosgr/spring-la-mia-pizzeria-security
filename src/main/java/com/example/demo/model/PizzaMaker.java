package com.example.demo.model;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.*;

@SuppressWarnings("unused")

@Entity
@Table(name = "pizzaMakers")

public class PizzaMaker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// Viene usata la Bean Validation

	@NotNull
	@NotEmpty
	@Size(min = 2, max = 100)
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@NotEmpty
	@Size(min = 2, max = 100)
	@Column(name = "surname", nullable = false)
	private String surname;

	@NotNull
	@Min(18)
	@Column(name = "age", nullable = false)
	private int age;

	@NotNull
	private boolean freelancer;

	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	// Relazione One To Many con Pizza
    @OneToMany(mappedBy = "pizzaMaker", cascade = CascadeType.ALL)
    private List<Pizza> pizzasCooked;

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isFreelancer() {
		return freelancer;
	}

	public void setFreelancer(boolean freelancer) {
		this.freelancer = freelancer;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
