package com.tcc.cadastro.services;

import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.cadastro.feignClients.CardFeignClients;
import com.tcc.cadastro.models.Account;
import com.tcc.cadastro.repositories.AccountRepository;
import com.tcc.cadastro.vo.AccountVO;
import com.tcc.cadastro.vo.CardInput;

import io.micrometer.core.instrument.util.StringUtils;

@Service
public class AccountService {

	@Autowired
	AccountRepository repository;
	
	@Autowired
	CardFeignClients cardFeignClients;
	
	public JSONObject createAccount(AccountVO vo) {
		JSONObject responseJson = new JSONObject();
		try {
			Account model = new Account();
			model = voToModel(vo, model);
			repository.save(model);
			
			cardFeignClients.generateCard(new CardInput("", "7055" + GenerationNumbers(0, 9, 19), model.getCpf(), "MULTIPLE", "HOLDER"));
			
			responseJson.put("code", "201").put("message", "Account created");
		}catch (Exception e) {
			responseJson.put("code", "500").put("message", "internal server error");
		}
		
		return responseJson;
	}
	
	
	public JSONObject updateAccount(AccountVO vo) {
		JSONObject responseJson = new JSONObject();
		try {
			Account modelInDb = repository.findByCpf(vo.getCpf());
			Account model = updateCheck(vo, modelInDb);
			
			repository.saveAndFlush(model);
			
			responseJson.put("code", "200").put("message", "Account updated");
		}catch (Exception e) {
			responseJson.put("code", "500").put("message", "internal server error");
		}
		
		return responseJson;
	}
	
	public JSONObject consultAccount(String cpf) {
		JSONObject responseJson = new JSONObject();
		try {
			
			Account model = repository.findByCpf(cpf);
			
			if(model == null) {
				return responseJson.put("code", "404").put("message", "account not found");
			}
			
			responseJson = new JSONObject(model);
			responseJson.put("code", "200").put("message", "account sucessfuly found!");
			
		}catch (Exception e) {
			responseJson.put("code", "500").put("message", "internal server error");
		}
		return responseJson;
	}
	
	private Account voToModel(AccountVO vo, Account account) {
		account.setCpf(vo.getCpf());
		account.setName(vo.getName());
		account.setMotherName(vo.getMotherName());
		account.setBirthDate(vo.getBirthDate());
		account.setGender(vo.getGender());
		account.setNationalId(vo.getNationalId());
		account.setAddressHome(vo.getAddressHome());
		account.setAddressNumber(vo.getAddressNumber());
		account.setAddressComplement(vo.getAddressComplement());
		account.setCellPhone(vo.getCellPhone());
		account.setPhone(vo.getPhone());
		account.setPassword(vo.getPassword());
		return account;
	}
	
	private Account updateCheck(AccountVO vo, Account accountInDb) {
		Account accountForUpdate = new Account();
		accountForUpdate.setCpf(StringUtils.isEmpty(vo.getCpf())? accountInDb.getCpf() : vo.getCpf());
		accountForUpdate.setName(StringUtils.isEmpty(vo.getName()) ? accountInDb.getName() : vo.getName());
		accountForUpdate.setMotherName(StringUtils.isEmpty(vo.getMotherName()) ? accountInDb.getMotherName() : vo.getMotherName());
		accountForUpdate.setBirthDate(StringUtils.isEmpty(vo.getBirthDate()) ? accountInDb.getBirthDate() : vo.getBirthDate());
		accountForUpdate.setGender(StringUtils.isEmpty(vo.getGender()) ? accountInDb.getGender() : accountInDb.getGender());
		accountForUpdate.setNationalId(StringUtils.isEmpty(vo.getNationalId()) ? accountInDb.getNationalId() : vo.getNationalId());
		accountForUpdate.setAddressHome(StringUtils.isEmpty(vo.getAddressHome()) ? accountInDb.getAddressHome() : vo.getAddressHome());
		accountForUpdate.setAddressNumber(StringUtils.isEmpty(vo.getAddressNumber()) ? accountInDb.getAddressNumber() : vo.getAddressNumber());
		accountForUpdate.setAddressComplement(StringUtils.isEmpty(vo.getAddressComplement()) ? accountInDb.getAddressComplement() : vo.getAddressComplement());
		accountForUpdate.setCellPhone(StringUtils.isEmpty(vo.getCellPhone()) ? accountInDb.getCellPhone() : vo.getCellPhone());
		accountForUpdate.setPhone(StringUtils.isEmpty(vo.getPhone()) ? accountInDb.getPhone() : vo.getPhone());
		accountForUpdate.setPassword(StringUtils.isEmpty(vo.getPassword()) ? accountInDb.getPassword() : vo.getPassword());
		accountForUpdate.setId(accountInDb.getId());
		return accountForUpdate;
	}
	
	public String GenerationNumbers(int min,int max,int quantity) {
		String numberGenerated = "";
		
		for(int i = 0; i < 12; i++) {
			Integer number = ThreadLocalRandom.current().nextInt(min, max + 1);
			numberGenerated += number.toString();
		}
		
		return numberGenerated;
	}
	
}
