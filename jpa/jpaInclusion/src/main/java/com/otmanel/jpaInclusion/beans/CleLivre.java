package com.otmanel.jpaInclusion.beans;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString 
@EqualsAndHashCode
public class CleLivre implements Serializable {
	@Column(length=15)		private String isbn;
							private LocalDate dateParution;
}
