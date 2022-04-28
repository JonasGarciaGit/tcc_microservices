package com.tcc.cardsmanagement.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tcc.cardsmanagement.input.CardInput;

@Entity
@Table(name = "CARDS")
public class CardModel implements Serializable{
	
	private static final long serialVersionUID = -4785917721035736825L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "CARD_NUMBER")
	private String cardNumber;
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;
	@Column(name = "DOCUMENT_NUMBER")
	private String documentNumber;
	@Column(name = "PRODUCT_TYPE")
	private String productType;
	@Column(name = "CARD_HOLDER_TYPE")
	private String cardHolderType;
	@Column(name = "VALID_THRU")
	private String validThru;
	
	public CardModel() {
		
	}

	public CardModel(BigDecimal id, String cardNumber, String accountNumber, String documentNumber, String productType,
			String cardHolderType, String validThru) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.accountNumber = accountNumber;
		this.documentNumber = documentNumber;
		this.productType = productType;
		this.cardHolderType = cardHolderType;
		this.validThru = validThru;
	}
	
	public CardModel(CardInput input) {
		this.accountNumber = input.getAccountNumber();
		this.documentNumber = input.getDocumentNumber();
		this.productType = input.getProductType();
		this.cardHolderType = input.getCardHolderType();
		this.validThru = input.getValidThru();
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
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

	public String getValidThru() {
		return validThru;
	}

	public void setValidThru(String validThru) {
		this.validThru = validThru;
	}
	
	
	
}
