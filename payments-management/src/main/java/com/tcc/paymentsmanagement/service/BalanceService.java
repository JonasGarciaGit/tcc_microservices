package com.tcc.paymentsmanagement.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tcc.paymentsmanagement.input.BalanceInput;
import com.tcc.paymentsmanagement.input.BalanceManagementInput;
import com.tcc.paymentsmanagement.model.BalanceModel;
import com.tcc.paymentsmanagement.repository.BalanceRepository;

@Service
public class BalanceService {

	@Autowired
	private BalanceRepository repository;
	
	public JSONObject newBalance(BalanceInput input) {
		JSONObject response = new JSONObject();
		
		try {
			BalanceModel model = new BalanceModel(input);
			model = repository.save(model);
			
			if(model == null) {
				response.put("returnCode", "422").put("returnDescription", "failed to recharge balance");
				return response;
			}
			
			response.put("returnCode", "200").put("returnDescription", "first charge succefully made");
			
		}catch (Exception e) {
			response.put("returnCode", "1000").put("returnDescription", "Internal server error");
		}
		
		return response;
	}

	
	public JSONObject getBalance(String documentNumber, String accountNumber) {
		JSONObject response = new JSONObject();
		
		try {
			BalanceModel model = new BalanceModel();
			
			if(!StringUtils.isEmpty(documentNumber)) {
				model = repository.findByDocumentNumber(documentNumber);
			}else if(!StringUtils.isEmpty(accountNumber)) {
				model = repository.findByAccountNumber(accountNumber);
			}else {
				response.put("returnCode", "422").put("returnDescription", "Please insert an accountNumber or documentNumber");
			}
			
			if(model == null) {
				response.put("returnCode", "404").put("returnDescription", "Not found");
				return response;
			}
			
			response = new JSONObject(model);
			response.put("returnCode", "200").put("returnDescription", "Found with success");
			
		}catch (Exception e) {
			response.put("returnCode", "1000").put("returnDescription", "Internal server error");
		}
		
		return response;
	}
	
	public JSONObject balanceManagement(BalanceManagementInput input, String accountNumber) {
		JSONObject response = new JSONObject();
		
		try {
			BalanceModel model = new BalanceModel();
			
			if(StringUtils.isEmpty(input.getBalance())) {
				input.setBalance(0.0);
			}
			
			if(StringUtils.isEmpty(accountNumber)) {
				response.put("returnCode", "422").put("returnDescription", "accountNumber can't be null or empty");
				return response;
			}
			
			model = repository.findByAccountNumber(accountNumber);
			
			if(model == null) {
				response.put("returnCode", "404").put("returnDescription", "Not found");
				return response;
			}
			
			if(input.getBalance() < 0.0) {
				model.setBalance(model.getBalance() - Double.parseDouble(input.getBalance().toString().replace("-", "")));
			}else if(input.getBalance() > 0.0) {
				model.setBalance(model.getBalance() + input.getBalance());
			}
			
			model = repository.save(model);
			
			if(model == null) {
				response.put("returnCode", "422").put("returnDescription", "failed to recharge balance");
				return response;
			}
			
			response.put("returnCode", "200").put("returnDescription", "Charge succefully made");
			
		}catch (Exception e) {
			response.put("returnCode", "1000").put("returnDescription", "Internal server error");
		}
		
		return response;
	}
}
