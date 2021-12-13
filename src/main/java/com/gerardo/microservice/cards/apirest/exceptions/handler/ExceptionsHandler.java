package com.gerardo.microservice.cards.apirest.exceptions.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
		response.put("message", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	/**
	 * Captura el error de cuando hace falta un parámetro en la petición de tarjetas.
	 * @param ex objeto de tipo MissingSerlvletRequestParameterException
	 * @return regresa un objeto de tipo ResponseEntity
	 */
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ResponseEntity<Object> missingRequestParameters(MissingServletRequestParameterException ex) {
		Map<String, Object> response = new HashMap<>();
		
		response.put("message", "Olvidaste agregar alguno de los siguientes parámatros a la petición");
		response.put("param", ex.getParameterName());
		response.put("paramType", ex.getParameterType());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	/**
	 * Captura el error de cuando el usuario envía un tipo de dato distinto
	 * del esperado en alguno de los parámetros.
	 * @param ex objeto de tipo MethodArgumentTypeMismatchException.
	 * @return regresa un objeto de tipo ResponseEntity.
	 */
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> paramTypeMismatch(MethodArgumentTypeMismatchException ex) {
		Map<String, Object> response = new HashMap<>();
		
		response.put("message", "El tipo de dato que enviaste no coincide con el esperado");
		response.put("param", ex.getName());
		response.put("paramTypeExpected", ex.getParameter().getParameterType());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
}
