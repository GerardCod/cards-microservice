package com.gerardo.microservice.cards;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.gerardo.microservice.cards.model.entities.Card;
import com.gerardo.microservice.cards.model.services.CardsService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CardServiceTests {

  @Autowired
  private CardsService service;

  @Test
  @DisplayName("When the age is under 18, it should return an empty optional")
  void ageUnderEighteenReturnEmpty() {
    Optional<List<Card>> result = service.processProfile("shopping", new BigDecimal(8000), 17);
    assertTrue(result.isEmpty());
  }
  
  @Test
  @DisplayName("When the salary is under 7000, it should return an empty optional")
  void salaryUnderSevenThousandReturnEmpty() {
    Optional<List<Card>> result = service.processProfile("shopping", new BigDecimal(6000), 20);
    assertTrue(result.isEmpty());
  }
  
  @Test
  @DisplayName("When the passion is invalid, it should return an empty optional")
  void invalidPassionReturnEmpty() {
    Optional<List<Card>> result = service.processProfile("languages", new BigDecimal(8000), 20);
    assertTrue(result.isEmpty());
  }
  
  @Test
  @DisplayName("When the profile is invalid, it should return an empty optional")
  void invalidProfileReturnEmpty() {
    Optional<List<Card>> result = service.processProfile("languages", new BigDecimal(6000), 17);
    assertTrue(result.isEmpty());
  }

  @Test
  @DisplayName("When the age is above of 75, it should return an empty optional")
  void ageAboveSeventyFiveReturnEmpty() {
    Optional<List<Card>> result = service.processProfile("shopping", new BigDecimal(30000), 78);
    assertTrue(result.isEmpty());
  }

  @Test
  @DisplayName("When the profile is valid, is should return an optional with a list of cards")
  void validProfileReturnList() {
    Optional<List<Card>> result = service.processProfile("shopping", new BigDecimal(8000), 22);
    System.out.println(result);
    assertTrue(result.isPresent());
  }
}
