package com.gerardo.microservice.cards.apirest.exceptions.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gerardo.microservice.cards.apirest.exceptions.NotFoundCardApplicableException;

/**
 * Controlador general de errores.
 * @author Gerardo Aguilar
 * @version 1.0.0
 */
@ControllerAdvice
public class ExceptionsHandler {
	
	/**
	 * Procesa la excepción cuando no se encuentran tarjetas aplicables al perfíl del usuario.
	 * @param ex excepción de tipo NotFoundCardApplicableException.
	 * @return regresa la respuesta del error.
	 */
	@ExceptionHandler(value = NotFoundCardApplicableException.class)
	public ResponseEntity<Object> notFoundCardApplicable(NotFoundCardApplicableException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("error", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	/**
	 * Procesa la excepción cuando el usuario ingresa información incorrecta.
	 * @param ex excepción de tipo BadRequestException.
	 * @return regresa la respuesta del error.
	 */
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Object> badRequest(ConstraintViolationException ex) {
		Map<String, Object> response = new HashMap<>();

		List<ConstraintViolation<?>> errorsList = ex.getConstraintViolations().stream().toList();

		response.put("cause", ex.getMessage());
		response.put("errors", errorsList);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
}
