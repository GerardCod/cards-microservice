package com.gerardo.microservice.cards.apirest.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@Autowired
	private CardsService service;
	
	/**
	 * Petición post que permite conocer al usuario los tipos de tarjetas a los que puede aplicar.
	 * @param input Cuerpo de la petición.
	 * @param validationResult Resultado de la validación del cuerpo de la petición.
	 * @return
	 * <ul>
	 * 	<li>200: Regresa un objeto ResponseEntity con la información de la tarjeta aplicable al usuario.</li>
	 *  <li>400: Regresa un código badrequest cuando el perfíl del usuario si es menor de edad o si tiene un ingreso mensual menor a 7000.</li>
	 *  <li>404: Regresa un código notfound cuando el perfíl del usuario no es aplicable a alguna de las tarjetas.</li>
	 * </ul>
	 */
	@GetMapping
	public ResponseEntity<List<Card>> cardRequest(
		@RequestParam("passion")
		@NotNull(message = "Tu interés no puede ser nulo")
		@NotBlank(message = "Tu interés no puede estar vacío")
		String passion,
		
		@RequestParam("salary")
		@NotNull(message = "Tu salario no puede estar vacío")
		@Min(value = 7000, message = "Tu salario mensual no puede ser menor a 7000.00")
		BigDecimal monthlySalary,

		@RequestParam("age")
		@NotNull(message = "Tu edad no puede ser nula")
		@Min(value = 18, message = "No puedes aplicar si eres menor de edad")
		Integer age
	) {
		
		Optional<List<Card>> cardApplicable = service.processProfile(passion, monthlySalary, age);
		
		if (!cardApplicable.isPresent()) {
			throw new NotFoundCardApplicableException("No puedes aplicar a alguna de las tarjetas");
		}
		
		return ResponseEntity.ok(cardApplicable.get());
	}
	
}
