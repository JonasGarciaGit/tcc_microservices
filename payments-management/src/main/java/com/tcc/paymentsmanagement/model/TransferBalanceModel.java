package com.tcc.paymentsmanagement.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tcc.paymentsmanagement.input.TransferBalanceInput;

@Entity
@Table(name = "TRANSFER_BALANCE")
public class TransferBalanceModel implements Serializable {

	private static final long serialVersionUID = 8580879842091314189L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "PAYER_ACCOUNT_NUMBER")
	private String payerAccountNumber;
	@Column(name = "PAYER_DOCUMENT_NUMBER")
	private String payerDocumentNumber;
	@Column(name = "TRANSFERRED_BALANCE")
	private String transferredBalance;
	@Column(name = "RECIPIENT_DOCUMENT_NUMBER")
	private String recipientDocumentNumber;
	@Column(name = "RECIPIENT_ACCOUNT_NUMBER")
	private String recipientAccountNumber;
	@Column(name = "DATE")
	private String date;
	
	public TransferBalanceModel() {
	}
	
	public TransferBalanceModel(TransferBalanceInput input) {
		this.payerAccountNumber = input.getPayerAccountNumber();
		this.payerDocumentNumber = input.getPayerDocumentNumber();
		this.transferredBalance = input.getTransferredBalance();
		this.recipientDocumentNumber = input.getRecipientDocumentNumber();
		this.recipientAccountNumber = input.getRecipientAccountNumber();
		this.date = input.getDate();
	}

	public TransferBalanceModel(BigDecimal id, String payerAccountNumber, String payerDocumentNumber,
			String transferredBalance, String recipientDocumentNumber, String recipientAccountNumber, String date) {
		super();
		this.id = id;
		this.payerAccountNumber = payerAccountNumber;
		this.payerDocumentNumber = payerDocumentNumber;
		this.transferredBalance = transferredBalance;
		this.recipientDocumentNumber = recipientDocumentNumber;
		this.recipientAccountNumber = recipientAccountNumber;
		this.date = date;
	}


	public BigDecimal getId() {
		return id;
	}


	public void setId(BigDecimal id) {
		this.id = id;
	}


	public String getPayerAccountNumber() {
		return payerAccountNumber;
	}

	public void setPayerAccountNumber(String payerAccountNumber) {
		this.payerAccountNumber = payerAccountNumber;
	}

	public String getPayerDocumentNumber() {
		return payerDocumentNumber;
	}

	public void setPayerDocumentNumber(String payerDocumentNumber) {
		this.payerDocumentNumber = payerDocumentNumber;
	}

	public String getTransferredBalance() {
		return transferredBalance;
	}

	public void setTransferredBalance(String transferredBalance) {
		this.transferredBalance = transferredBalance;
	}

	public String getRecipientDocumentNumber() {
		return recipientDocumentNumber;
	}

	public void setRecipientDocumentNumber(String recipientDocumentNumber) {
		this.recipientDocumentNumber = recipientDocumentNumber;
	}

	public String getRecipientAccountNumber() {
		return recipientAccountNumber;
	}

	public void setRecipientAccountNumber(String recipientAccountNumber) {
		this.recipientAccountNumber = recipientAccountNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
