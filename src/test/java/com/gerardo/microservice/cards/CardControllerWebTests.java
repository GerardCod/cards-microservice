package com.gerardo.microservice.cards;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gerardo.microservice.cards.apirest.controllers.CardsController;
import com.gerardo.microservice.cards.model.entities.Card;
import com.gerardo.microservice.cards.model.services.CardsService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CardsController.class)
public class CardControllerWebTests {
  
  @Autowired
  private MockMvc mvc;

  @MockBean
  private CardsService service;

  private static List<Card> cards;

  @BeforeAll
  static void init() {
    cards = new ArrayList<>();

    Card cardOne = new Card();
    cardOne.setId(1L);
    cardOne.setMinMonthlySalary(new BigDecimal(7000.00));
    cardOne.setMaxMonthlySalary(new BigDecimal(14999.00));
    cardOne.setMinAge(18);
    cardOne.setMaxAge(23);
    cardOne.setCardName("B*smart");

    Card cardTwo = new Card();
    cardTwo.setId(2L);
    cardTwo.setMinMonthlySalary(new BigDecimal(7000.00));
    cardTwo.setMaxMonthlySalary(new BigDecimal(14999.00));
    cardTwo.setMinAge(18);
    cardTwo.setMaxAge(23);
    cardTwo.setCardName("Card Afinity");

    cards.add(cardOne);
    cards.add(cardTwo);
  }

  @Test
  @DisplayName("When the profile is valid, it should return cards")
  void validProfileShouldReturnCards() throws Exception {

    when(service.processProfile("shopping", new BigDecimal(8000), 18))
      .thenReturn(Optional.of(cards));

    mvc.perform(get("/api/v1/cards")
    		.queryParam("passion", "shopping")
    		.queryParam("salary", "8000")
    		.queryParam("age", "18"))
    .andExpect(status().isOk());
  }
  
  @Test
  @DisplayName("When there is a param missing, it should return status 400")
  void missingParamShouldThrowException() throws Exception {
	  
	  mvc.perform(get("/api/v1/cards")
			  .queryParam("passion", "Shopping")
			  .queryParam("salary", "8000"))
	  .andExpect(status().isBadRequest());
	  
  }
}
