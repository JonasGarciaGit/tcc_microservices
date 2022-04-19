package com.tcc.paymentsmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.paymentsmanagement.model.BalanceModel;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceModel, String>{

	BalanceModel findByDocumentNumber(String documentNumber);
	BalanceModel findByAccountNumber(String accountNumber);
}
