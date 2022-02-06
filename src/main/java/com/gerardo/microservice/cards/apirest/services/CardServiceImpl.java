package com.gerardo.microservice.cards.apirest.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.gerardo.microservice.cards.apirest.exceptions.NotFoundCardApplicableException;
import com.gerardo.microservice.cards.apirest.model.entities.Card;
import com.gerardo.microservice.cards.apirest.repositories.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardServiceImpl implements CardsService {
	
	private final CardsRepository repository;

	@Autowired
	public CardServiceImpl(CardsRepository repository) {
		this.repository = repository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Card> processProfile(String passion, BigDecimal monthlySalary, Integer age) {
		List<Card> result = repository.findCardByProfile(passion.toUpperCase(), monthlySalary, age);
		
		if (result.isEmpty()) {
			throw new NotFoundCardApplicableException("No hay tarjetas aplicables para tu perfil");
		}
		
		return result;
	}

}
