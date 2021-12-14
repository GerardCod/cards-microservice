package com.gerardo.microservice.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.gerardo.microservice.cards.model.entities.Card;
import com.gerardo.microservice.cards.model.repositories.CardsRepository;
import com.gerardo.microservice.cards.model.services.CardServiceImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CardServiceUnitTests {
  
  @Mock
  private CardsRepository repository;

  @InjectMocks
  private CardServiceImpl service;

  @Test
  @DisplayName("The service should be created")
  void serviceIsCreated() {
    assertTrue(service != null);
  }

  @Test
  @DisplayName("When the profile is valid, it should return cards")
  void validProfileShouldReturnCards() { 
    Optional<List<Card>> result = service.processProfile("shopping", new BigDecimal(8000), 18);
    assertEquals(2, result.get().size());
  }

}
