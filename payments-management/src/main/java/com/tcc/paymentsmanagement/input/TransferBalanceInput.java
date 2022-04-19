package com.tcc.paymentsmanagement.input;

public class TransferBalanceInput {

	private String payerAccountNumber;
	private String payerDocumentNumber;
	private String transferredBalance;
	private String recipientDocumentNumber;
	private String recipientAccountNumber;
	private String date;
	
	
	public TransferBalanceInput() {
		
	}
	
	
	public TransferBalanceInput(String payerAccountNumber, String payerDocumentNumber, String transferredBalance,
			String recipientDocumentNumber, String recipientAccountNumber, String date) {
		super();
		this.payerAccountNumber = payerAccountNumber;
		this.payerDocumentNumber = payerDocumentNumber;
		this.transferredBalance = transferredBalance;
		this.recipientDocumentNumber = recipientDocumentNumber;
		this.recipientAccountNumber = recipientAccountNumber;
		this.date = date;
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
