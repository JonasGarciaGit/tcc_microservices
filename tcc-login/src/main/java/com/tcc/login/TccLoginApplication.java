package com.tcc.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import com.tcc.login.controller.LoginController;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class TccLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(TccLoginApplication.class, args);
	}

}
