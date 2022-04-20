package com.tcc.cadastro.vo;

public class CardInput {

	private String cardNumber;
	private String accountNumber;
	private String documentNumber;
	private String productType;
	private String cardHolderType;
	
	public CardInput() {
		
	}

	
	public CardInput(String cardNumber, String accountNumber, String documentNumber, String productType,
			String cardHolderType) {
		super();
		this.cardNumber = cardNumber;
		this.accountNumber = accountNumber;
		this.documentNumber = documentNumber;
		this.productType = productType;
		this.cardHolderType = cardHolderType;
	}



	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getCardHolderType() {
		return cardHolderType;
	}

	public void setCardHolderType(String cardHolderType) {
		this.cardHolderType = cardHolderType;
	}
	
	
	
}
