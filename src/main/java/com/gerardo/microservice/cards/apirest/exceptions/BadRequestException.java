package com.gerardo.microservice.cards.apirest.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;

import com.gerardo.microservice.cards.apirest.model.dto.ServiceInputError;

import lombok.Getter;

/**
 * Clase para indicar cuando un usuario envíe una petición mal formada
 * @author Gerardo Aguilar
 * @version 1.0.0
 */
@Getter
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 5832283091203682008L;

	private List<ServiceInputError> errors;
	
	public BadRequestException(String message, List<FieldError> errors) {
		super(message);
		this.errors = errors.stream().map(e -> new ServiceInputError(e)).collect(Collectors.toList());
	}
	
}
