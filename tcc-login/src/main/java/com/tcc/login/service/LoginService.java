package com.tcc.login.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tcc.login.feignclients.AccountFeignClients;
import com.tcc.login.utils.Utils;
import com.tcc.login.vo.AccountVO;

@Service
public class LoginService {

	@Autowired
	AccountFeignClients accountFeign;
	
	public JSONObject loginAuthenticate(AccountVO input) {
		JSONObject returnJson = new JSONObject();
		try {
			
			if(StringUtils.isEmpty(input.getPassword())) {
				return returnJson.put("code", "422").put("message", "password can't be null or empty!");
			}
			
			if(StringUtils.isEmpty(input.getCpf())) {
				return returnJson.put("code", "422").put("message", "document can't be null or empty!");
			}
			
			input.setPassword(Utils.criptoPassword(input.getPassword()));
			
			ResponseEntity<String> requestResult = accountFeign.consultAccount(input.getCpf());
			JSONObject requestToJson = new JSONObject(requestResult.getBody());
			
			if(!requestToJson.getString("code").equals("200")) {
				return returnJson.put("code", "500").put("message", "there was an error querying the password");
			}
			
			String passwordObtained = requestToJson.isNull("password") ? "999999" : requestToJson.get("password").toString();
			
			if(input.getPassword().equals(passwordObtained)) {
				return returnJson.put("code", "200").put("message", "true");
			}else {
				return returnJson.put("code", "200").put("message", "false");
			}
			
		}catch (Exception e) {
			return returnJson.put("code", "500").put("message", "internal server error");
		}
	}
	
	
	public JSONObject firstAccess(AccountVO input) {
		JSONObject returnJson = new JSONObject();
		try {
			
			if(StringUtils.isEmpty(input.getPassword())) {
				return returnJson.put("code", "422").put("message", "password can't be null or empty!");
			}
			
			if(StringUtils.isEmpty(input.getCpf())) {
				return returnJson.put("code", "422").put("message", "document can't be null or empty!");
			}
			
			
			ResponseEntity<String> requestResult = accountFeign.updateAccount(input);
			returnJson = new JSONObject(requestResult.getBody());
			
		}catch (Exception e) {
			return returnJson.put("code", "500").put("message", "internal server error");
		}
		return returnJson;
	}
	
	public JSONObject updatePassword(AccountVO input) {
		JSONObject returnJson = new JSONObject();
		try {
			
			if(StringUtils.isEmpty(input.getPassword()) || StringUtils.isEmpty(input.getNewPassword()) || StringUtils.isEmpty(input.getConfirmationPassword())) {
				return returnJson.put("code", "422").put("message", "password, new password or confirmation password is null or empty!");
			}
			
			if(StringUtils.isEmpty(input.getCpf())) {
				return returnJson.put("code", "422").put("message", "document can't be null or empty!");
			}
			
			if(input.getPassword().equals(input.getNewPassword())) {
				return returnJson.put("code", "422").put("message", "new password can't be equals actually password!");
			}
			
			if(!input.getNewPassword().equals(input.getConfirmationPassword())) {
				return returnJson.put("code", "422").put("message", "new password and confirmation password must be the same");
			}
			
			input.setPassword(input.getNewPassword());
			
			ResponseEntity<String> requestResult = accountFeign.updateAccount(input);
			returnJson = new JSONObject(requestResult.getBody());
			
		}catch (Exception e) {
			return returnJson.put("code", "500").put("message", "internal server error");
		}
		return returnJson;
	}
	
}
