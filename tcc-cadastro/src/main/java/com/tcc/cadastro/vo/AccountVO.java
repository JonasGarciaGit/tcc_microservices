package com.tcc.cadastro.vo;

public class AccountVO {

	private Integer id;

	private String cpf;

	private String name;

	private String motherName;

	private String birthDate;

	private String gender;

	private String nationalId;

	private String addressHome;

	private String addressNumber;

	private String addressComplement;

	private String cellPhone;

	private String phone;
	
	private String password;

	public AccountVO(Integer id, String cpf, String name, String motherName, String birthDate, String gender,
			String nationalId, String addressHome, String addressNumber, String addressComplement, String cellPhone,
			String phone, String password) {
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
		this.password = password;
	}

	public AccountVO() {
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
	
	
	
}
