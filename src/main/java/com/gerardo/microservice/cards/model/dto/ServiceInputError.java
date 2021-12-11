package com.gerardo.microservice.cards.model.dto;

import org.springframework.validation.FieldError;

import lombok.Data;

@Data
public class ServiceInputError {
	private String message;
	private String field;
	private String invalidValue;
	
	public ServiceInputError(FieldError error) {
		this.message = error.getDefaultMessage();
		this.field = error.getField();
		this.invalidValue = error.getRejectedValue().toString();
	}
	
}
