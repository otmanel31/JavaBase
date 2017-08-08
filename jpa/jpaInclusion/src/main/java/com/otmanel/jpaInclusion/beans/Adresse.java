package com.otmanel.jpaInclusion.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// ce nest donc pas uneentité et jpa ne generera pas de table de cette entité
// et pas de pk
@Embeddable // "inclusionnable" ;)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Builder
public class Adresse {
	@Column(length=100) 	private String rue;
	@Column(length=100)		private String ville;
	@Column(length=20)		private String codePostal;
	@Column(length=100) 	private String pays;
}
