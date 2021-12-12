package com.gerardo.microservice.cards.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerardo.microservice.cards.model.dto.ServiceInput;
import com.gerardo.microservice.cards.model.entities.Card;
import com.gerardo.microservice.cards.model.repositories.CardsRepository;

@Service
public class CardServiceImpl implements CardsService {
	
	@Autowired
	private CardsRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public Optional<List<Card>> processProfile(ServiceInput userProfile) {
		List<Card> result = repository.findCardByProfile(userProfile.getPassion(), userProfile.getMonthlySalary(), userProfile.getAge());
		
		System.out.println(result);
		
		if (result != null && !result.isEmpty()) {
			return Optional.of(result);
		}
		
		return Optional.empty();
	}

}
