package com.gerardo.microservice.cards.apirest.exceptions.handler;

import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.gerardo.microservice.cards.apirest.exceptions.NotFoundCardApplicableException;

/**
 * Controlador para procesar los errores de los controladores.
 * @author Gerardo Aguilar
 * @version 1.0.0
 */
@RestControllerAdvice
public class ExceptionsHandler {
	
	/**
	 * Captura el error cuando no se encuentran tarjetas aplicables a un cliente.
	 * @param ex excepción de tipo NotFoundCardApplicableException.
	 * @return regresa la respuesta del error.
	 */
	@ExceptionHandler(value = NotFoundCardApplicableException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> notFoundCardApplicable(NotFoundCardApplicableException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("message", ex.getMessage());
		return response;
	}
	
	/**
	 * Captura el error cuando hace falta un parámetro en la petición de tarjetas.
	 * @param ex objeto de tipo MissingSerlvletRequestParameterException
	 * @return regresa un objeto de tipo ResponseEntity
	 */
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> missingRequestParameters(MissingServletRequestParameterException ex) {
		Map<String, Object> response = new HashMap<>();
		
		response.put("message", "Olvidaste agregar alguno de los siguientes parámatros a la petición");
		response.put("param", ex.getParameterName());
		response.put("paramType", ex.getParameterType());
		return response;
	}
	
	/**
	 * Captura el error de cuando el usuario envía un tipo de dato distinto
	 * del esperado en alguno de los parámetros.
	 * @param ex objeto de tipo MethodArgumentTypeMismatchException.
	 * @return regresa un objeto de tipo ResponseEntity.
	 */
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> paramTypeMismatch(MethodArgumentTypeMismatchException ex) {
		Map<String, Object> response = new HashMap<>();
		
		response.put("message", "El tipo de dato que enviaste no coincide con el esperado");
		response.put("param", ex.getName());
		response.put("paramTypeExpected", ex.getParameter().getParameterType());
		return response;
	}
	
}
