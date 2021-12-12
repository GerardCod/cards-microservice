package com.gerardo.microservice.cards.model.services;

import java.util.List;
import java.util.Optional;

import com.gerardo.microservice.cards.model.dto.ServiceInput;
import com.gerardo.microservice.cards.model.entities.Card;

public interface CardsService {
	Optional<List<Card>> processProfile(ServiceInput userProfile);
}
