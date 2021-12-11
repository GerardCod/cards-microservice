package com.gerardo.microservice.cards.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerardo.microservice.cards.model.dto.CardType;
import com.gerardo.microservice.cards.model.dto.ServiceInput;
import com.gerardo.microservice.cards.model.entities.Card;
import com.gerardo.microservice.cards.model.repositories.CardsRepository;

@Service
public class CardServiceImpl implements CardsService {
	
	@Autowired
	private CardsRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public Optional<CardType> processProfile(ServiceInput userProfile) {
		Card result = repository.findCardByProfile(userProfile.getPassion(), userProfile.getMonthlySalary(), userProfile.getAge());
		
		System.out.println(result);
		
		if (result != null) {
			return Optional.of(new CardType(result.getCardsApplicables()));
		}
		
		return Optional.empty();
	}

}
