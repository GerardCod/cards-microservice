package com.gerardo.microservice.cards.apirest.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInput {
	
	@NotNull(message = "Tu pasión no puede ser nula")
	@NotEmpty(message = "Tu pasión no puede estar vacía")
	private String passion;
	
	@NotNull(message = "Tu salario mensual no puede ser nulo")
	@Min(value = 7000)
	private BigDecimal monthlySalary;
	
	@NotNull(message = "Tu edad no puede ser nula")
	@Min(value = 18)
	private Integer age;
}
