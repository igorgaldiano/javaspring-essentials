package com.devsuperior.terceirodesafio.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.terceirodesafio.dto.ClientDTO;
import com.devsuperior.terceirodesafio.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientControllers {
	
	@Autowired
	private ClientService service;
	
	
	@GetMapping(value = "/{id}")
	public ClientDTO findById(@PathVariable Long id) {
		ClientDTO dto = service.findById(id);
		return dto;
	}
	
	@GetMapping
	public Page<ClientDTO> findAll(Pageable pageable){
		return service.findAll(pageable);
	
	}

}
