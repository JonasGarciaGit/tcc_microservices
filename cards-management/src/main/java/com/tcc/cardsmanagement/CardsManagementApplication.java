package com.tcc.cardsmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CardsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsManagementApplication.class, args);
	}

}
