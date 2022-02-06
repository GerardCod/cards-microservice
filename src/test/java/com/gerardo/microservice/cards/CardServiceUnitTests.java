package com.gerardo.microservice.cards;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gerardo.microservice.cards.apirest.model.entities.Card;

import com.gerardo.microservice.cards.apirest.services.CardsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CardServiceUnitTests {
  @Mock
  private CardsService service;

  private static List<Card> cards;

  @BeforeAll
  static void init() {
    cards = new ArrayList<>();

    Card cardOne = new Card();
    cardOne.setId(1L);
    cardOne.setMinMonthlySalary(new BigDecimal(7000));
    cardOne.setMaxMonthlySalary(new BigDecimal(14999));
    cardOne.setMinAge(18);
    cardOne.setMaxAge(23);
    cardOne.setCardName("B*smart");

    Card cardTwo = new Card();
    cardTwo.setId(2L);
    cardTwo.setMinMonthlySalary(new BigDecimal(7000));
    cardTwo.setMaxMonthlySalary(new BigDecimal(14999));
    cardTwo.setMinAge(18);
    cardTwo.setMaxAge(23);
    cardTwo.setCardName("Card Afinity");

    cards.add(cardOne);
    cards.add(cardTwo);
  }

  @Test
  @DisplayName("The service should be created")
  void serviceIsCreated() {
    assertTrue(service != null);
  }

  @Test
  @DisplayName("When the profile is valid, it should return a list of cards")
  void validProfileShouldReturnCards() {

    doReturn(Optional.of(cards)).when(service).processProfile("shopping", new BigDecimal(8000), 18);

    List<Card> result = service.processProfile("shopping", new BigDecimal(8000), 18);
    assertEquals(2, result.size());
    assertIterableEquals(cards, result);
  }

  @Test
  @DisplayName("When the age is invalid, it should return an empty list")
  void invalidAgeShouldReturnEmpty() {

    doReturn(Optional.empty()).when(service).processProfile("shopping", new BigDecimal(8000), 17);

    List<Card> result = service.processProfile("shopping", new BigDecimal(8000), 17);
    assertTrue(result.isEmpty());
  }

  @Test
  @DisplayName("When the salary is invalid, it should return an empty list")
  void invalidSalaryShouldReturnEmpty() {
    when(service.processProfile("shopping", new BigDecimal(6000), 18)).thenReturn(new ArrayList<Card>());

    List<Card> result = service.processProfile("shopping", new BigDecimal(6000), 18);
    assertTrue(result.isEmpty());
  }

  @Test
  @DisplayName("When the passion is invalid, it should return an empty list")
  void invalidPassionShouldReturnEmpty() {
    doReturn(Optional.empty()).when(service).processProfile("languages", new BigDecimal(8000), 20);

    List<Card> result = service.processProfile("languages", new BigDecimal(8000), 20);
    assertTrue(result.isEmpty());
  }

  @Test
  @DisplayName("When the profile is invalid, it should return an empty list")
  void invalidProfileShouldReturnEmpty() {
    doReturn(Optional.empty()).when(service).processProfile("languages", new BigDecimal(6500), 78);

    List<Card> result = service.processProfile("languages", new BigDecimal(6500), 78);
    assertTrue(result.isEmpty());
  }
}
