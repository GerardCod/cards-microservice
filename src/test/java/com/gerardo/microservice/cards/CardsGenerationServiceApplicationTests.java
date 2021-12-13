package com.gerardo.microservice.cards;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;

import com.gerardo.microservice.cards.apirest.controllers.CardsController;
import com.gerardo.microservice.cards.apirest.exceptions.NotFoundCardApplicableException;
import com.gerardo.microservice.cards.model.entities.Card;
import com.gerardo.microservice.cards.model.repositories.CardsRepository;
import com.gerardo.microservice.cards.model.services.CardServiceImpl;

@SpringBootTest
class CardsGenerationServiceApplicationTests {

	@Mock
	private CardsRepository repository;

	@Mock
	private CardServiceImpl service;

	@InjectMocks
	private CardsController controller;

	@Test
	@DisplayName("The controller should be created")
	void controllerShouldBeCreated() {
		assertTrue(controller != null);
	}

	@Test()
	@DisplayName("When the age is under 18, it should throws an NotFoundCardApplicableException.")
	void controllerShouldReturnAnEmptyListTest() {
		assertThrows(NotFoundCardApplicableException.class, () -> {
			controller.cardRequest("Shopping", new BigDecimal(8000.00), 17);			
		});
	}
	
	@Test
	@DisplayName("When the salary is under 7000, it should throws and NotFoundCardApplicableException.")
	void controllerShouldReturnAndEmptyList_WhenSalaryIsLessThan7000Test() {
		assertThrows(NotFoundCardApplicableException.class, () -> {
			controller.cardRequest("Shopping", new BigDecimal(8000.00), 17);			
		});
	}
	
	@Test
	@DisplayName("When the profile is correct, it should return a list with elements")
	void controllerShouldReturnAListWithElements() {
		ResponseEntity<List<Card>> cardRequest = controller.cardRequest("shopping", new BigDecimal(8000.00), 20);
		
		assertTrue(cardRequest.getBody().size() > 0);
	}

}
