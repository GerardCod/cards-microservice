package com.gerardo.microservice.cards;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;

import com.gerardo.microservice.cards.apirest.controllers.CardsController;
import com.gerardo.microservice.cards.apirest.exceptions.NotFoundCardApplicableException;
import com.gerardo.microservice.cards.apirest.model.entities.Card;

@SpringBootTest
class CardsGenerationServiceApplicationTests {

	@Autowired
	private CardsController controller;



	@Test
	@DisplayName("Given a valid profile, when it process the profile, then returns cards")
	void getCards() {
		ResponseEntity<List<Card>> actual = controller.cardRequest("shopping", new BigDecimal(8000), 18);
		assertEquals(2, actual.getBody().size());
	}

	@Test
	@DisplayName("Given an invalid profile throws an exception")
	void throwsExceptionWhenMissingQueryParam() {
		assertThrows(NotFoundCardApplicableException.class, () -> {
			controller.cardRequest("Music", new BigDecimal(2000), 15);
		});
	}
}
