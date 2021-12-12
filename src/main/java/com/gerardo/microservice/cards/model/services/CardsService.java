package com.gerardo.microservice.cards.model.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.gerardo.microservice.cards.model.entities.Card;

public interface CardsService {
	Optional<List<Card>> processProfile(String passion, BigDecimal monthlySalary, Integer age);
}
