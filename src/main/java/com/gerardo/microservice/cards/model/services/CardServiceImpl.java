package com.gerardo.microservice.cards.model.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerardo.microservice.cards.model.entities.Card;
import com.gerardo.microservice.cards.model.repositories.CardsRepository;

@Service
public class CardServiceImpl implements CardsService {
	
	private CardsRepository repository;

	@Autowired
	public CardServiceImpl(CardsRepository repository) {
		this.repository = repository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<List<Card>> processProfile(String passion, BigDecimal monthlySalary, Integer age) {
		List<Card> result = repository.findCardByProfile(passion.toUpperCase(), monthlySalary, age);
		
		if (result != null && !result.isEmpty()) {
			return Optional.of(result);
		}
		
		return Optional.empty();
	}

}
