package com.devsuperior.terceirodesafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.terceirodesafio.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
