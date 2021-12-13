package com.gerardo.microservice.cards.apirest.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gerardo.microservice.cards.apirest.exceptions.NotFoundCardApplicableException;
import com.gerardo.microservice.cards.model.entities.Card;
import com.gerardo.microservice.cards.model.services.CardsService;

@RestController
@RequestMapping("/cards")
@Validated
public class CardsController {
	
	private CardsService service;

	@Autowired
	public CardsController(CardsService service) {
		this.service = service;
	}
	
	/**
	 * Petición get que permite conocer al usuario los tipos de tarjetas a los que puede aplicar.
	 * @param passion pasatiempo o pasión del usuario.
	 * @param monthlySalary salario mensual del usuario.
	 * @param age edad del usuario.
	 * @return
	 * <ul>
	 * 	<li>200: Regresa un objeto ResponseEntity con la información de la tarjeta aplicable al usuario.</li>
	 *  <li>400: Regresa un código badrequest cuando el perfíl del usuario si es menor de edad o si tiene un ingreso mensual menor a 7000.</li>
	 *  <li>404: Regresa un código notfound cuando el perfíl del usuario no es aplicable a alguna de las tarjetas.</li>
	 * </ul>
	 */
	@GetMapping
	public ResponseEntity<List<Card>> cardRequest(
		@RequestParam(name = "passion", required = true)
		String passion,
		
		@RequestParam(name = "salary", required = true)
		BigDecimal monthlySalary,

		@RequestParam(name = "age", required = true)
		Integer age
	) {
		
		Optional<List<Card>> cardApplicable = service.processProfile(passion, monthlySalary, age);
		
		if (cardApplicable.isEmpty()) {
			throw new NotFoundCardApplicableException("No puedes aplicar a alguna de las tarjetas");
		}
		
		return ResponseEntity.ok(cardApplicable.get());
	}
	
	
}
