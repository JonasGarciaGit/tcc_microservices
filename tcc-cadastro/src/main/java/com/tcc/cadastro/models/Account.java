package com.tcc.cadastro.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tcc.cadastro.vo.AccountVO;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {

	private static final long serialVersionUID = -4796369797754551616L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "UUID")
	private String uuid;
	
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;
	
	@Column(name = "CPF")
	private String cpf;

	@Column(name = "NAME")
	private String name;

	@Column(name = "MOTHER_NAME")
	private String motherName;

	@Column(name = "BIRTH_DATE")
	private String birthDate;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "NATIONAL_ID")
	private String nationalId;

	@Column(name = "ADDRESS_HOME")
	private String addressHome;

	@Column(name = "ADDRESS_NUMBER")
	private String addressNumber;

	@Column(name = "ADDRESS_COMPLEMENT")
	private String addressComplement;
	
	@Column(name = "CEP")
	private String cep;

	@Column(name = "CELLPHONE")
	private String cellPhone;

	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "PASSWORD")
	private String password;

	public Account(Integer id, String cpf, String name, String motherName, String birthDate, String gender,
			String nationalId, String addressHome, String addressNumber, String addressComplement, String cellPhone,
			String phone, String cep, String uuid, String accountNumber) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.name = name;
		this.motherName = motherName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.nationalId = nationalId;
		this.addressHome = addressHome;
		this.addressNumber = addressNumber;
		this.addressComplement = addressComplement;
		this.cellPhone = cellPhone;
		this.phone = phone;
		this.cep = cep;
		this.uuid = uuid;
		this.accountNumber = accountNumber;
	}

	public Account() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getAddressHome() {
		return addressHome;
	}

	public void setAddressHome(String addressHome) {
		this.addressHome = addressHome;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getAddressComplement() {
		return addressComplement;
	}

	public void setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
}
