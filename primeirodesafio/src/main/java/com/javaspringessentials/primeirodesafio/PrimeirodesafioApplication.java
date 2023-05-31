package com.javaspringessentials.primeirodesafio;

import java.util.Locale;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javaspringessentials.primeirodesafio.entities.Order;
import com.javaspringessentials.primeirodesafio.services.OrderService;

@SpringBootApplication
public class PrimeirodesafioApplication implements CommandLineRunner{
	
	@Autowired
	private OrderService orderService;
	

	public PrimeirodesafioApplication(OrderService orderService) {
		this.orderService = orderService;
	}

	public static void main(String[] args) {
		
	
		Locale.setDefault(Locale.US);
		
		SpringApplication.run(PrimeirodesafioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	
		
		Order order1 = new Order(1034,150.0,20.0);
		System.out.println(" Pedido código " + order1.getCode());
		System.out.println(" Valor total " + orderService.total(order1));
		System.out.println();
				Order order2 = new Order(2282,800.00,10.0);
		System.out.println(" Pedido código " + order2.getCode());
		System.out.println(" Valor total " + orderService.total(order2));
		System.out.println();
		Order order3 = new Order(1309,95.90, 0.0);
		System.out.println(" Pedido código " + order3.getCode());
		System.out.println(" Valor total " + orderService.total(order3));
		
	}

}
