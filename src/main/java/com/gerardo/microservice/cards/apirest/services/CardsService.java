package com.gerardo.microservice.cards.apirest.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.gerardo.microservice.cards.apirest.model.entities.Card;

public interface CardsService {
	List<Card> processProfile(String passion, BigDecimal monthlySalary, Integer age);
}
