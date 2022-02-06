package com.gerardo.microservice.cards.apirest.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gerardo.microservice.cards.apirest.model.entities.Card;

@Repository
public interface CardsRepository extends CrudRepository<Card, Long> {

	@Query("select c from Card c where c.passion = ?1 and ?2 between c.minMonthlySalary and c.maxMonthlySalary and ?3 between c.minAge and c.maxAge")
	public List<Card> findCardByProfile(String passion, BigDecimal salary, Integer age);
	
}
