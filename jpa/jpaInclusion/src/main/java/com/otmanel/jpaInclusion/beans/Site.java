package com.otmanel.jpaInclusion.beans;

import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.otmanel.jpaInclusion.utils.LocalisationConverter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Site {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id 														private int id;
																private String nom;
	@Embedded													private Adresse adresse;
	@Convert(converter=LocalisationConverter.class)				private Geolocalisation localisation;
}
