package com.gerardo.microservice.cards;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.gerardo.microservice.cards.apirest.model.entities.Card;
import com.gerardo.microservice.cards.apirest.services.CardsService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CardServiceTests {

  @Autowired
  private CardsService service;

  @Test
  @DisplayName("When the age is under 18, it should return an empty list")
  void ageUnderEighteenReturnEmpty() {
    List<Card> result = service.processProfile("shopping", new BigDecimal(8000), 17);
    assertTrue(result.isEmpty());
  }
  
  @Test
  @DisplayName("When the salary is under 7000, it should return an empty list")
  void salaryUnderSevenThousandReturnEmpty() {
    List<Card> result = service.processProfile("shopping", new BigDecimal(6000), 20);
    assertTrue(result.isEmpty());
  }
  
  @Test
  @DisplayName("When the passion is invalid, it should return an empty optional")
  void invalidPassionReturnEmpty() {
    List<Card> result = service.processProfile("languages", new BigDecimal(8000), 20);
    assertTrue(result.isEmpty());
  }
  
  @Test
  @DisplayName("When the profile is invalid, it should return an empty list")
  void invalidProfileReturnEmpty() {
    List<Card> result = service.processProfile("languages", new BigDecimal(6000), 17);
    assertTrue(result.isEmpty());
  }

  @Test
  @DisplayName("When the age is above of 75, it should return an empty list")
  void ageAboveSeventyFiveReturnEmpty() {
    List<Card> result = service.processProfile("shopping", new BigDecimal(30000), 78);
    assertTrue(result.isEmpty());
  }

  @Test
  @DisplayName("When the profile is valid, is should return a list of cards")
  void validProfileReturnList() {
    List<Card> result = service.processProfile("shopping", new BigDecimal(8000), 22);
    System.out.println(result);
    assertTrue(!result.isEmpty());
  }
}
