package com.tcc.cardsmanagement.input;

public class BalanceInput {

	private String accountNumber;
	private Double balance;
	private String documentNumber;
	private String cardNumber;
	
	public BalanceInput() {
	}

	
	
	
	public BalanceInput(String accountNumber, Double balance, String documentNumber, String cardNumber) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.documentNumber = documentNumber;
		this.cardNumber = cardNumber;
	}




	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
}
