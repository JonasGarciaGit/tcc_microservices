package com.tcc.cadastro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.cadastro.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	Account findByCpf(String cpf);
	
}
