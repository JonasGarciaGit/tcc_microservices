package com.tcc.paymentsmanagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tcc.paymentsmanagement.input.TransferBalanceInput;
import com.tcc.paymentsmanagement.model.BalanceModel;
import com.tcc.paymentsmanagement.model.TransferBalanceModel;
import com.tcc.paymentsmanagement.repository.BalanceRepository;
import com.tcc.paymentsmanagement.repository.TransferBalanceRepository;

@Service
public class TransferBalanceService {

	@Autowired
	private TransferBalanceRepository repository;
	
	@Autowired
	private BalanceRepository balanceRepository;
	
	public JSONObject newTransaction(TransferBalanceInput input) {
		JSONObject response = new JSONObject();
		
		try {
			TransferBalanceModel model = new TransferBalanceModel(input);
			response = managementTransactionMti(input, model);
			
			if(!"200".equals(response.get("returnCode").toString())) {
				return response;
			}
			
			
			model.setDate(new Date().toString());
			model = repository.save(model);

			if(model == null && "200".equals(response.get("returnCode").toString())) {
				response.put("returnCode", "422").put("returnDescription", "failed to save transaction history");
				return response;
			}else if(model == null && !"200".equals(response.get("returnCode").toString())) {
				response.put("returnCode", "422").put("returnDescription", "transaction failed");
				return response;
			}
			
			response.put("returnCode", "200").put("returnDescription", "successful transaction");
			
		}catch (Exception e) {
			response.put("returnCode", "1000").put("returnDescription", "Internal server error");
		}
		
		return response;
	}
	
	
	public JSONObject consultTransaction(String documentNumber, String accountNumber) {
		JSONObject response = new JSONObject();		
		JSONArray listJson = new JSONArray();
		
		try {
			List<TransferBalanceModel> transactions = new ArrayList<>();
			
			if(!StringUtils.isEmpty(documentNumber)) {
				transactions = repository.listAllTransactionByDocumentNumber(documentNumber);
			}else if(!StringUtils.isEmpty(accountNumber)) {
				transactions = repository.listAllTransactionByAccountNumber(accountNumber);
			}else {
				response.put("returnCode", "422").put("returnDescription", "documentNumber or accountNumber must be informed");
				return response;
			}
			
			if(transactions.isEmpty()) {
				response.put("returnCode", "404").put("returnDescription", "transactions not found");
				return response;
			}
			
			
			for(TransferBalanceModel model : transactions) {
				listJson.put(new JSONObject(model));
			}
			
			response.put("transactions", listJson);
			response.put("returnCode", "200").put("returnDescription", "transactions found");
			
		}catch (Exception e) {
			response.put("returnCode", "1000").put("returnDescription", "Internal server error");
		}
		return response;
	}
	
	public JSONObject managementTransactionMti(TransferBalanceInput input, TransferBalanceModel inputModel) {
		
		JSONObject response = new JSONObject();
		BalanceModel balanceModelPayer = new BalanceModel();
		BalanceModel balanceModelRecipient = new BalanceModel();
		
		if(!StringUtils.isEmpty(input.getPayerDocumentNumber())) {
			balanceModelPayer = balanceRepository.findByDocumentNumber(input.getPayerDocumentNumber());
		}else if(!StringUtils.isEmpty(input.getPayerAccountNumber())) {
			balanceModelPayer = balanceRepository.findByAccountNumber(input.getPayerAccountNumber());
		}else {
			response.put("returnCode", "422").put("returnDescription", "Please insert an accountNumber or documentNumber for payer");
			return response;
		}
		
		if(!StringUtils.isEmpty(input.getRecipientDocumentNumber())) {
			balanceModelRecipient = balanceRepository.findByDocumentNumber(input.getRecipientDocumentNumber());
		}else if(!StringUtils.isEmpty(input.getRecipientAccountNumber())) {
			balanceModelRecipient = balanceRepository.findByAccountNumber(input.getRecipientAccountNumber());
		}else {
			response.put("returnCode", "422").put("returnDescription", "Please insert an accountNumber or documentNumber for recipient");
			return response;
		}
		 
		if(balanceModelRecipient == null || balanceModelPayer == null) {
			response.put("returnCode", "404").put("returnDescription", "Not found");
			return response;
		}
		
		
		if(balanceModelPayer.getBalance() - Double.parseDouble(input.getTransferredBalance()) < 0.0) {
			response.put("returnCode", "422").put("returnDescription", "insufficient funds");
			return response;
		}
		
		balanceModelPayer.setBalance(balanceModelPayer.getBalance() - Double.parseDouble(input.getTransferredBalance())); 
		balanceModelRecipient.setBalance(balanceModelRecipient.getBalance() + Double.parseDouble(input.getTransferredBalance()));
		
		balanceRepository.save(balanceModelPayer);
		balanceRepository.save(balanceModelRecipient);
		
		inputModel.setPayerAccountNumber(balanceModelPayer.getAccountNumber());
		inputModel.setPayerDocumentNumber(balanceModelPayer.getDocumentNumber());
		inputModel.setRecipientAccountNumber(balanceModelRecipient.getAccountNumber());
		inputModel.setRecipientDocumentNumber(balanceModelRecipient.getDocumentNumber());
		
		response.put("returnCode", "200").put("returnDescription", "OK");
		return response;
	}
}
