package com.tcc.cadastro.controllers;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.cadastro.services.AccountService;
import com.tcc.cadastro.utils.HttpStatusUtils;
import com.tcc.cadastro.vo.AccountVO;

@RestController
public class AccountController {

	@Autowired
	AccountService service;

	@PostMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createAccount(@RequestBody AccountVO input) {
		JSONObject responseJson = new JSONObject();
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			responseJson = service.createAccount(input);
			String code = responseJson.isNull("code") ? "500" : responseJson.getString("code");
			httpStatus = HttpStatusUtils.validHttpStatus(code);
		} catch (Exception e) {
			responseJson.put("code", "500").put("message", "internal server error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(responseJson.toString(), httpStatus);
	}
	
	@GetMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> consultAccount(
			@RequestHeader String cpf) {
		JSONObject responseJson = new JSONObject();
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			responseJson = service.consultAccount(cpf);
			String code = responseJson.isNull("code") ? "500" : responseJson.getString("code");
			httpStatus = HttpStatusUtils.validHttpStatus(code);
		} catch (Exception e) {
			responseJson.put("code", "500").put("message", "internal server error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(responseJson.toString(), httpStatus);
	}
	
	@PutMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateAccount(@RequestBody AccountVO input) {
		JSONObject responseJson = new JSONObject();
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			responseJson = service.updateAccount(input);
			String code = responseJson.isNull("code") ? "500" : responseJson.getString("code");
			httpStatus = HttpStatusUtils.validHttpStatus(code);
		} catch (Exception e) {
			responseJson.put("code", "500").put("message", "internal server error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(responseJson.toString(), httpStatus);
	}

}
