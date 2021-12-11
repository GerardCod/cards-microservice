package com.gerardo.microservice.cards.apirest.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerardo.microservice.cards.apirest.exceptions.BadRequestException;
import com.gerardo.microservice.cards.apirest.exceptions.NotFoundCardApplicableException;
import com.gerardo.microservice.cards.model.dto.CardType;
import com.gerardo.microservice.cards.model.dto.ServiceInput;
import com.gerardo.microservice.cards.model.services.CardsService;

@RestController
@RequestMapping("/cards")
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
	@PostMapping
	public ResponseEntity<CardType> cardRequest(@Valid @RequestBody ServiceInput input, BindingResult validationResult) {
		
		if (validationResult.hasErrors()) {
			throw new BadRequestException("Cuerpo de la petición mal formado", validationResult.getFieldErrors());
		}
		
		Optional<CardType> cardApplicable = service.processProfile(input);
		
		if (!cardApplicable.isPresent()) {
			throw new NotFoundCardApplicableException("No puedes aplicar a alguna de las tarjetas");
		}
		
		return ResponseEntity.ok(cardApplicable.get());
	}
	
}
