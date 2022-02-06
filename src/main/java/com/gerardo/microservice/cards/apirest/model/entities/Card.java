package com.gerardo.microservice.cards.apirest.model.entities;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Card{");
		sb.append("id=").append(id);
		sb.append(", passion='").append(passion).append('\'');
		sb.append(", minMonthlySalary=").append(minMonthlySalary);
		sb.append(", maxMonthlySalary=").append(maxMonthlySalary);
		sb.append(", minAge=").append(minAge);
		sb.append(", maxAge=").append(maxAge);
		sb.append(", cardName='").append(cardName).append('\'');
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return id.equals(card.id) && passion.equals(card.passion) && cardName.equals(card.cardName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, passion, cardName);
	}
}
