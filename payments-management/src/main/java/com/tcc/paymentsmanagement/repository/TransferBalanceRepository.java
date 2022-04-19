package com.tcc.paymentsmanagement.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tcc.paymentsmanagement.model.TransferBalanceModel;

@Repository
public interface TransferBalanceRepository extends JpaRepository<TransferBalanceModel, BigDecimal> {

	@Query("select bal from TransferBalanceModel bal where bal.payerAccountNumber = :accountNumber OR bal.recipientAccountNumber = :accountNumber")
	public List<TransferBalanceModel> listAllTransactionByAccountNumber(@Param("accountNumber") String accountNumber);
	
	@Query("select bal from TransferBalanceModel bal where bal.payerDocumentNumber = :documentNumber OR bal.recipientDocumentNumber = :documentNumber")
	public List<TransferBalanceModel> listAllTransactionByDocumentNumber(@Param("documentNumber") String documentNumber);
}
