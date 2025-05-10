package com.devsuperior.terceirodesafio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.devsuperior.terceirodesafio.dto.ClientDTO;
import com.devsuperior.terceirodesafio.entities.Client;
import com.devsuperior.terceirodesafio.repositories.ClientRepository;
import com.devsuperior.terceirodesafio.services.exceptions.DataBaseException;
import com.devsuperior.terceirodesafio.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;



@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public ClientDTO findById (Long Id) {
		
	
		Client client = repository.findById(Id).
		orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado"));
		 return new ClientDTO(client);
	}
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable){
	
		Page<Client> result = repository.findAll(pageable);
		return result.map(x-> new ClientDTO(x));
}
	
	@Transactional()
	public ClientDTO insert(ClientDTO dto) {
		
		Client entity = new Client();
		copyDtoEntity(dto,entity);
		
		entity = repository.save(entity);
		
		return new ClientDTO(entity);
	}
	
	@Transactional()
	public ClientDTO update(Long id,ClientDTO dto) {
		
		try {
			Client entity = repository.getReferenceById(id);
			copyDtoEntity(dto,entity);
			entity = repository.save(entity);
			return new ClientDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException ("Recurso não encontrado");
			
		}
		
		}
		
	
	
	public void delete(Long Id) {
	   
		if (!repository.existsById(Id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		
		
        	repository.deleteById(Id);  		
		  		
    	
}
		
	
	
	
	private void copyDtoEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		
	}
	
	
}
