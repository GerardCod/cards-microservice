package com.gerardo.microservice.cards.model.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String passion;
	
	@Column(nullable = false)
	private BigDecimal minMonthlySalary;
	
	@Column(nullable = false)
	private BigDecimal maxMonthlySalary;
	
	@Column(nullable = false)
	private Integer minAge;
	
	@Column(nullable = false)
	private Integer maxAge;
	
	@Column(nullable = false)
	private String cardName;
}
