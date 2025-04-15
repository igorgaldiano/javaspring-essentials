package com.devsuperior.terceirodesafio.dto;

import java.time.LocalDate;


import org.springframework.stereotype.Service;

import com.devsuperior.terceirodesafio.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;


@Service
public class ClientDTO {
	private Long id;
	@NotBlank(message = "campo não pode ser vazio")
	private String name;
	
	private String cpf;
	private Double income;
	@PastOrPresent(message = "não pode ser data futura")
	private LocalDate birthDate;
	private Integer children;
	
	public ClientDTO() {
		
	}

	public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
	
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public ClientDTO (Client entity) {
		id = entity.getId();
		name = entity.getName();
		cpf = entity.getCpf();
		income = entity.getIncome();
		birthDate = entity.getBirthDate();
		children = entity.getChildren();
	}

	
	//DTO somente GETTERS (não há alteração de dados)
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}
	
	
}
