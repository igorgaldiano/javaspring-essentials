package com.devsuperior.terceirodesafio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.terceirodesafio.dto.ClientDTO;
import com.devsuperior.terceirodesafio.entities.Client;
import com.devsuperior.terceirodesafio.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public ClientDTO findById (Long Id) {
		
	
		Client client = repository.findById(Id).get();
		 return new ClientDTO(client);
	}
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable){
	
		Page<Client> result = repository.findAll(pageable);
		return result.map(x-> new ClientDTO(x));
}
	
	
	
}
