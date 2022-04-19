package com.tcc.login.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.login.service.LoginService;
import com.tcc.login.utils.HttpStatusUtils;
import com.tcc.login.vo.AccountVO;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	LoginService service;
	
	
	@PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> loginAuthenticate(@RequestBody AccountVO input) {
		JSONObject responseJson = new JSONObject();
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			responseJson = service.loginAuthenticate(input);
			String code = responseJson.isNull("code") ? "500" : responseJson.getString("code");
			httpStatus = HttpStatusUtils.validHttpStatus(code);
		} catch (Exception e) {
			responseJson.put("code", "500").put("message", "internal server error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(responseJson.toString(), httpStatus);
	}
	
	
	@PutMapping(value = "/first-access", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> firstAccess(@RequestBody AccountVO input) {
		JSONObject responseJson = new JSONObject();
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			responseJson = service.firstAccess(input);
			String code = responseJson.isNull("code") ? "500" : responseJson.getString("code");
			httpStatus = HttpStatusUtils.validHttpStatus(code);
		} catch (Exception e) {
			responseJson.put("code", "500").put("message", "internal server error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(responseJson.toString(), httpStatus);
	}
	
	@PutMapping(value = "/update-password", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updatePassword(@RequestBody AccountVO input) {
		JSONObject responseJson = new JSONObject();
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			responseJson = service.updatePassword(input);
			String code = responseJson.isNull("code") ? "500" : responseJson.getString("code");
			httpStatus = HttpStatusUtils.validHttpStatus(code);
		} catch (Exception e) {
			responseJson.put("code", "500").put("message", "internal server error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(responseJson.toString(), httpStatus);
	}
	
}
