package com.tcc.cadastro.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcc.cadastro.vo.CardInput;

@Component
@FeignClient(name = "cards-management")
public interface CardFeignClients {

	@PostMapping(value = "/card", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> generateCard(@RequestBody CardInput input);
		
	
}
