package com.gerardo.microservice.cards.model.services;

import java.util.Optional;

import com.gerardo.microservice.cards.model.dto.CardType;
import com.gerardo.microservice.cards.model.dto.ServiceInput;

public interface CardsService {
	Optional<CardType> processProfile(ServiceInput userProfile);
}
