package com.tcc.cardsmanagement.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tcc.cardsmanagement.feignClients.PaymentsFeignClients;
import com.tcc.cardsmanagement.input.BalanceInput;
import com.tcc.cardsmanagement.input.CardInput;
import com.tcc.cardsmanagement.model.CardModel;
import com.tcc.cardsmanagement.repository.CardRepository;
import com.tcc.cardsmanagement.utils.Util;

@Service
public class CardService {

	@Autowired
	CardRepository repository;
	
	@Autowired
	PaymentsFeignClients paymentClients;
	
	
	
	public JSONObject generateCard(CardInput input) {
		JSONObject response = new JSONObject();
		try {
			
			CardModel model = new CardModel(input);
			String cardNumberGenerated = "5502" + GenerationNumbers(0, 9, 12);
			
			if("5502".equals(cardNumberGenerated)) {
				response.put("returnCode","422").put("returnDescription","Card number generation fail, please try again.");
				return response;
			}
			
			
			model.setValidThru(getValidThru());
			model.setCardNumber(cardNumberGenerated);
			
			model = repository.save(model);
		
			if(model == null) {
				response.put("returnCode","422").put("returnDescription","Creation card failed");
				return response;
			}
				
				
			response.put("returnCode","201").put("returnDescription","Card was created with successfully");
			paymentClients.newBalance(new BalanceInput(model.getAccountNumber(),0.0,model.getDocumentNumber(),model.getCardNumber()));
			
			
		}catch (Exception e) {
			response.put("returnCode","1000").put("returnDescription","Internal server error");
		}
		
		return response;
	}
	
	
	public JSONObject getGenerateCard(String documentNumber) {
		JSONObject response = new JSONObject();
		
		try {
			CardModel model = null;
			
			if(StringUtils.isEmpty(documentNumber)) {
				response.put("returnCode","422").put("returnDescription","documentNumber can't be null or empty");
				return response;
			}
			
			model = repository.findByDocumentNumber(documentNumber);
			
			if(model == null) {
				response.put("returnCode","404").put("returnDescription","card not found");
				return response;
			}
			
			response = new JSONObject(model);
			response.put("returnCode", "200").put("returnDescription", "card found with success");
			
		}catch (Exception e) {
			response.put("returnCode","1000").put("returnDescription","Internal server error");
		}
		
		return response;
	}
	
	
	public String GenerationNumbers(int min,int max,int quantity) {
		String numberGenerated = "";
		
		for(int i = 0; i < 12; i++) {
			Integer number = ThreadLocalRandom.current().nextInt(min, max + 1);
			numberGenerated += number.toString();
		}
		
		return numberGenerated;
	}
	
	public String getValidThru() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.YEAR, 8);
			date = cal.getTime();
			return sdf.format(date);
		}catch (Exception e) { return "00/00"; }
	}
}
