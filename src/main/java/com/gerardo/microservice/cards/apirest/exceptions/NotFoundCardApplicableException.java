package com.gerardo.microservice.cards.apirest.exceptions;

/**
 * Clase para indicar que el usuario no puede aplicar alguna de las tarjetas,
 * @author Gerardo Aguilar
 * @version 1.0.0
 */
public class NotFoundCardApplicableException extends RuntimeException {

	private static final long serialVersionUID = -9155195958570797818L;
	
	public NotFoundCardApplicableException(String message) {
		super(message);
	}
}
