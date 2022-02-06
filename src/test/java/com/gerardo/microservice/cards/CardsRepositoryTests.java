package com.gerardo.microservice.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import com.gerardo.microservice.cards.apirest.model.entities.Card;
import com.gerardo.microservice.cards.apirest.repositories.CardsRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CardsRepositoryTests {
  
  @Autowired
  private CardsRepository repository;

  @Test
  @DisplayName("When the profile is correct, should return a list with elements")
  void shouldReturnAList() {
    List<Card> cards = repository.findCardByProfile("shopping".toUpperCase(), new BigDecimal(9000), 20);
    
    assertTrue(cards.size() > 0);
  }
  
  @Test
  @DisplayName("When the age is under 18, it should return an empty list")
  void ageUnderEighteenShouldReturnEmpty() {
    List<Card> cards = repository.findCardByProfile("shopping".toUpperCase(), new BigDecimal(9000), 17);
    
    assertEquals(0, cards.size());
  }
  
  @Test
  @DisplayName("When the salary is under 7000, it should return an empty list")
  void salaryUnderSevenThousandShouldReturnEmpty() {
    List<Card> cards = repository.findCardByProfile("shopping".toUpperCase(), new BigDecimal(6000), 20);
    assertEquals(0, cards.size());
  }
  
  @Test
  @DisplayName("When the passion is invalid, it should return an empty list")
  void passionInvalidShouldReturnEmpty() {
    List<Card> cards = repository.findCardByProfile("languages".toUpperCase(), new BigDecimal(10000), 20);
    assertEquals(0, cards.size());
  }
  
  @Test
  @DisplayName("When the user profile is invalid, it should return an empty list")
  void invalidProfileShouldReturnEmpty() {
    List<Card> cards = repository.findCardByProfile("languages".toUpperCase(), new BigDecimal(5000), 17);
    assertEquals(0, cards.size());
  }

  @Test
  @DisplayName("When the age is above 75, it should return an empty list.")
  void ageAboveSeventyFiveShouldReturnEmpty() {
    List<Card> cards = repository.findCardByProfile("languages".toUpperCase(), new BigDecimal(5000), 17);
    assertEquals(0, cards.size());
  }
}
