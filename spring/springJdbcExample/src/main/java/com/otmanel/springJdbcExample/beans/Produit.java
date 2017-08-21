package com.otmanel.springJdbcExample.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor
public class Produit {
	private int id;
	private String nom;
	private double poids;
	private double prix;
	
	
}
