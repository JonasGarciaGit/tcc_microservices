package com.tcc.cadastro.services;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.cadastro.feignClients.CardFeignClients;
import com.tcc.cadastro.models.Account;
import com.tcc.cadastro.repositories.AccountRepository;
import com.tcc.cadastro.utils.Utils;
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
		Account model = null;
		try {

			responseJson = validateFieldsCreateAccount(vo);

			if (!"0".equals(responseJson.getString("code"))) {
				return responseJson;
			}

			if (!Utils.isCPF(vo.getCpf().replaceAll("[.\\-]", ""))) {
				return responseJson.put("code", "422").put("message", "inválid documentNumber!");
			}

			model = repository.findByCpf(vo.getCpf());
			if (model != null) {
				return responseJson.put("code", "422").put("message", "this account already exists!");
			}

			responseJson = new JSONObject();
			model = new Account();
			model = voToModel(vo, model);
			repository.save(model);

			cardFeignClients.generateCard(
					new CardInput("", "7055" + GenerationNumbers(0, 9, 19), model.getCpf(), "MULTIPLE", "HOLDER"));

			responseJson.put("code", "201").put("message", "Account created");

		} catch (Exception e) {
			responseJson.put("code", "500").put("message", "internal server error");
		}

		return responseJson;
	}

	public JSONObject updateAccount(AccountVO vo) {
		JSONObject responseJson = new JSONObject();
		try {

			if (StringUtils.isEmpty(vo.getCpf())) {
				return responseJson.put("code", "422").put("message",
						"the field documentNumber cant'be null or empty!");
			}

			Account modelInDb = repository.findByCpf(vo.getCpf());

			if (modelInDb == null) {
				return responseJson.put("code", "404").put("message", "account not found");
			}

			Account model = updateCheck(vo, modelInDb);

			repository.saveAndFlush(model);

			responseJson.put("code", "200").put("message", "Account updated");
		} catch (Exception e) {
			responseJson.put("code", "500").put("message", "internal server error");
		}

		return responseJson;
	}

	public JSONObject consultAccount(String cpf) {
		JSONObject responseJson = new JSONObject();
		try {

			if (StringUtils.isEmpty(cpf)) {
				return responseJson.put("code", "422").put("message",
						"the field documentNumber cant'be null or empty!");
			}

			Account model = repository.findByCpf(cpf);

			if (model == null) {
				return responseJson.put("code", "404").put("message", "account not found");
			}

			responseJson = new JSONObject(model);
			responseJson.put("code", "200").put("message", "account sucessfuly found!");

		} catch (Exception e) {
			responseJson.put("code", "500").put("message", "internal server error");
		}
		return responseJson;
	}

	public JSONObject deleteAccount(String documentNumber) {
		JSONObject response = new JSONObject();
		try {

			if (StringUtils.isEmpty(documentNumber)) {
				return response.put("code", "422").put("message", "the field documentNumber cant'be null or empty!");
			}

			Account modelInDb = repository.findByCpf(documentNumber);

			if (modelInDb == null) {
				return response.put("code", "404").put("message", "account not found");
			}
			
			repository.delete(modelInDb);
			response.put("code", "200").put("message", "account deleted sucessfuly!");

		} catch (Exception e) {
			response.put("code", "500").put("message", "internal server error");
		}
		return response;
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
		account.setPassword(Utils.criptoPassword(vo.getPassword()));
		account.setCep(vo.getCep());
		account.setAccountNumber(GenerationNumbers(0, 9, 19));
		account.setUuid(UUID.randomUUID().toString());
		account.setEmail(vo.getEmail());
		return account;
	}

	private Account updateCheck(AccountVO vo, Account accountInDb) {
		Account accountForUpdate = new Account();
		accountForUpdate.setCpf(StringUtils.isEmpty(vo.getCpf()) ? accountInDb.getCpf() : vo.getCpf());
		accountForUpdate.setName(StringUtils.isEmpty(vo.getName()) ? accountInDb.getName() : vo.getName());
		accountForUpdate.setMotherName(
				StringUtils.isEmpty(vo.getMotherName()) ? accountInDb.getMotherName() : vo.getMotherName());
		accountForUpdate
				.setBirthDate(StringUtils.isEmpty(vo.getBirthDate()) ? accountInDb.getBirthDate() : vo.getBirthDate());
		accountForUpdate
				.setGender(StringUtils.isEmpty(vo.getGender()) ? accountInDb.getGender() : accountInDb.getGender());
		accountForUpdate.setNationalId(
				StringUtils.isEmpty(vo.getNationalId()) ? accountInDb.getNationalId() : vo.getNationalId());
		accountForUpdate.setAddressHome(
				StringUtils.isEmpty(vo.getAddressHome()) ? accountInDb.getAddressHome() : vo.getAddressHome());
		accountForUpdate.setAddressNumber(
				StringUtils.isEmpty(vo.getAddressNumber()) ? accountInDb.getAddressNumber() : vo.getAddressNumber());
		accountForUpdate.setAddressComplement(
				StringUtils.isEmpty(vo.getAddressComplement()) ? accountInDb.getAddressComplement()
						: vo.getAddressComplement());
		accountForUpdate
				.setCellPhone(StringUtils.isEmpty(vo.getCellPhone()) ? accountInDb.getCellPhone() : vo.getCellPhone());
		accountForUpdate.setPhone(StringUtils.isEmpty(vo.getPhone()) ? accountInDb.getPhone() : vo.getPhone());
		accountForUpdate
				.setPassword(StringUtils.isEmpty(vo.getPassword()) ? accountInDb.getPassword() : vo.getPassword());
		accountForUpdate.setId(accountInDb.getId());
		accountForUpdate.setUuid(accountInDb.getUuid());
		accountForUpdate.setAccountNumber(accountInDb.getAccountNumber());
		accountForUpdate.setCep(StringUtils.isEmpty(vo.getCep()) ? accountInDb.getCep() : vo.getCep());
		accountForUpdate.setEmail(StringUtils.isEmpty(vo.getEmail()) ? accountInDb.getEmail() : vo.getEmail());
		return accountForUpdate;
	}

	public String GenerationNumbers(int min, int max, int quantity) {
		String numberGenerated = "";

		for (int i = 0; i < 12; i++) {
			Integer number = ThreadLocalRandom.current().nextInt(min, max + 1);
			numberGenerated += number.toString();
		}

		return numberGenerated;
	}

	public JSONObject validateFieldsCreateAccount(AccountVO vo) {
		JSONObject response = new JSONObject();
		try {

			if (StringUtils.isEmpty(vo.getCpf())) {
				return response.put("code", "422").put("message", "The field documentNumber can't be null or empty");
			}

			if (StringUtils.isEmpty(vo.getName())) {
				return response.put("code", "422").put("message", "The field name can't be null or empty");
			}

			if (StringUtils.isEmpty(vo.getBirthDate())) {
				return response.put("code", "422").put("message", "The field birthDate can't be null or empty");
			}

			if (StringUtils.isEmpty(vo.getNationalId())) {
				return response.put("code", "422").put("message", "The field nationalId can't be null or empty");
			}

			if (StringUtils.isEmpty(vo.getAddressHome())) {
				return response.put("code", "422").put("message", "The field addressHome can't be null or empty");
			}

			if (StringUtils.isEmpty(vo.getAddressNumber())) {
				return response.put("code", "422").put("message", "The field addressNumber can't be null or empty");
			}

			if (StringUtils.isEmpty(vo.getCellPhone())) {
				return response.put("code", "422").put("message", "The field cellPhone can't be null or empty");
			}

			if (StringUtils.isEmpty(vo.getPassword())) {
				return response.put("code", "422").put("message", "The field password can't be null or empty");
			}

			if (StringUtils.isEmpty(vo.getCep())) {
				return response.put("code", "422").put("message", "The field cep can't be null or empty");
			}

			if (StringUtils.isEmpty(vo.getEmail())) {
				return response.put("code", "422").put("message", "The field email can't be null or empty");
			}

		} catch (Exception e) {
			return response.put("code", "500").put("message", "internal server error");

		}
		return response.put("code", "0").put("message", "fields validated successfully");
	}
}
