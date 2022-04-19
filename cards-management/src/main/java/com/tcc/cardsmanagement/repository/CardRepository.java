package com.tcc.cardsmanagement.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.cardsmanagement.model.CardModel;

@Repository
public interface CardRepository extends JpaRepository<CardModel, BigDecimal>{

	CardModel findByDocumentNumber(String documentNumber);
}
