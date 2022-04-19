package com.tcc.login.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tcc.login.vo.AccountVO;

@Component
@FeignClient(name = "tcc-account", url = "localhost:8006")
public interface AccountFeignClients {

	@PutMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
	 ResponseEntity<String> updateAccount(@RequestBody AccountVO input);
	
	@GetMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> consultAccount(@RequestHeader String cpf);
	
}
