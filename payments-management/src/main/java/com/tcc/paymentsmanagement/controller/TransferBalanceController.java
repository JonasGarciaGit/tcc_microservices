package com.tcc.paymentsmanagement.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.paymentsmanagement.input.TransferBalanceInput;
import com.tcc.paymentsmanagement.service.TransferBalanceService;
import com.tcc.paymentsmanagement.utils.Util;

@RestController
public class TransferBalanceController {

	@Autowired
	private TransferBalanceService service;
	
	@PostMapping(value = "/transactions" , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> newTransaction(@RequestBody TransferBalanceInput input){
		JSONObject response = new JSONObject();
		try {
			
			response = service.newTransaction(input);
			String returnCode = response.isNull("returnCode") ? "1000" : response.get("returnCode").toString();
			return new ResponseEntity<String>(response.toString(), Util.validHttpStatus(returnCode));
			
		}catch (Exception e) {
			response.put("returnCode", "1000").put("returnDescription", "Internal server error");
			return new ResponseEntity<String>(response.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/transactions" , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> consultTransaction(@RequestHeader(required = false) String documentNumber,@RequestHeader(required = false) String accountNumber){
		JSONObject response = new JSONObject();
		try {
			
			response = service.consultTransaction(documentNumber, accountNumber);
			String returnCode = response.isNull("returnCode") ? "1000" : response.get("returnCode").toString();
			return new ResponseEntity<String>(response.toString(), Util.validHttpStatus(returnCode));
			
		}catch (Exception e) {
			response.put("returnCode", "1000").put("returnDescription", "Internal server error");
			return new ResponseEntity<String>(response.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
