package com.tcc.cadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TccCadastroApplication {

	public static void main(String[] args) {
		SpringApplication.run(TccCadastroApplication.class, args);
	}

}
