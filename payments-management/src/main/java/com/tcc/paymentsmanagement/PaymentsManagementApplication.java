package com.tcc.paymentsmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PaymentsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsManagementApplication.class, args);
	}

}
