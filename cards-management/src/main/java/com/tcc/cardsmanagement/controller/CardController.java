package com.tcc.cardsmanagement.controller;

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

import com.tcc.cardsmanagement.input.CardInput;
import com.tcc.cardsmanagement.service.CardService;
import com.tcc.cardsmanagement.utils.Util;

@RestController
public class CardController {

	@Autowired
	CardService service;
	
	@PostMapping(value = "/card", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> generateCard(@RequestBody CardInput input){
		JSONObject response = new JSONObject();
		try {
			response = service.generateCard(input);
			String returnCode = response.isNull("returnCode") ? "1000" : response.get("returnCode").toString();
			return new ResponseEntity<String>(response.toString(), Util.validHttpStatus(returnCode));
			
		}catch (Exception e) {
			response.put("returnCode", "1000").put("returnDescription", "Internal server error");
			return new ResponseEntity<String>(response.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/card", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> getGenerateCard(@RequestHeader String documentNumber){
		JSONObject response = new JSONObject();
		try {
			response = service.getGenerateCard(documentNumber);
			String returnCode = response.isNull("returnCode") ? "1000" : response.get("returnCode").toString();
			return new ResponseEntity<String>(response.toString(), Util.validHttpStatus(returnCode));
			
		}catch (Exception e) {
			response.put("returnCode", "1000").put("returnDescription", "Internal server error");
			return new ResponseEntity<String>(response.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
