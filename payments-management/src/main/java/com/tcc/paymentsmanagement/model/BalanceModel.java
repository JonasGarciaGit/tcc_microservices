package com.tcc.paymentsmanagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tcc.paymentsmanagement.input.BalanceInput;

@Entity
@Table(name = "BALANCE")
public class BalanceModel implements Serializable{
	private static final long serialVersionUID = 301400247796034167L;

	@Id
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;
	@Column(name = "BALANCE")
	private Double balance;
	@Column(name = "DOCUMENT_NUMBER")
	private String documentNumber;
	@Column(name = "CARD_NUMBER")
	private String cardNumber;
	
	public BalanceModel() {
	}

	
	
	public BalanceModel(String accountNumber, Double balance, String documentNumber, String cardNumber) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.documentNumber = documentNumber;
		this.cardNumber = cardNumber;
	}

	public BalanceModel(BalanceInput input) {
		this.accountNumber = input.getAccountNumber();
		this.balance = input.getBalance();
		this.documentNumber = input.getDocumentNumber();
		this.cardNumber = input.getCardNumber();
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

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
}
