package com.tcc.paymentsmanagement.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.paymentsmanagement.input.BalanceInput;
import com.tcc.paymentsmanagement.input.BalanceManagementInput;
import com.tcc.paymentsmanagement.service.BalanceService;
import com.tcc.paymentsmanagement.utils.Util;

@RestController
public class BalanceController {

	@Autowired
	private BalanceService service;
	
	@PostMapping(value = "/balance" , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> newBalance(@RequestBody BalanceInput input){
		JSONObject response = new JSONObject();
		try {
			
			response = service.newBalance(input);
			String returnCode = response.isNull("returnCode") ? "1000" : response.get("returnCode").toString();
			return new ResponseEntity<String>(response.toString(), Util.validHttpStatus(returnCode));
			
		}catch (Exception e) {
			response.put("returnCode", "1000").put("returnDescription", "Internal server error");
			return new ResponseEntity<String>(response.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/balance", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> getBalance(@RequestHeader(required = false) String documentNumber, @RequestHeader(required = false) String accountNumber){
		JSONObject response = new JSONObject();
		try {
			
			response = service.getBalance(documentNumber, accountNumber);
			String returnCode = response.isNull("returnCode") ? "1000" : response.get("returnCode").toString();
			return new ResponseEntity<String>(response.toString(), Util.validHttpStatus(returnCode));
			
		}catch (Exception e) {
			response.put("returnCode", "1000").put("returnDescription", "Internal server error");
			return new ResponseEntity<String>(response.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/balance/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> balanceManagement(@RequestBody BalanceManagementInput input, @PathVariable String accountNumber){
		JSONObject response = new JSONObject();
		try {
			
			response = service.balanceManagement(input, accountNumber);
			String returnCode = response.isNull("returnCode") ? "1000" : response.get("returnCode").toString();
			return new ResponseEntity<String>(response.toString(), Util.validHttpStatus(returnCode));
			
		}catch (Exception e) {
			response.put("returnCode", "1000").put("returnDescription", "Internal server error");
			return new ResponseEntity<String>(response.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
