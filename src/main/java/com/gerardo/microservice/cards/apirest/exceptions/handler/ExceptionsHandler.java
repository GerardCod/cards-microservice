package com.gerardo.microservice.cards.apirest.exceptions.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gerardo.microservice.cards.apirest.exceptions.BadRequestException;
import com.gerardo.microservice.cards.apirest.exceptions.NotFoundCardApplicableException;

@ControllerAdvice
public class ExceptionsHandler {
	
	@ExceptionHandler(value = NotFoundCardApplicableException.class)
	public ResponseEntity<Object> notFoundCardApplicable(NotFoundCardApplicableException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("error", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<Object> badRequest(BadRequestException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("cause", ex.getMessage());
		response.put("errors", ex.getErrors());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
}
