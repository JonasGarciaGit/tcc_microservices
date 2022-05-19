package com.tcc.cardsmanagement.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.cardsmanagement.input.BalanceInput;

@Component
@FeignClient("payments-management")
public interface PaymentsFeignClients {

	@PostMapping(value = "/balance" , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> newBalance(@RequestBody BalanceInput input);
	
}
