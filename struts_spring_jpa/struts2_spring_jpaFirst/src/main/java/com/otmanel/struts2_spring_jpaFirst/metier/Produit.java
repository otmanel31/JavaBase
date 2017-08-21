package com.otmanel.struts2_spring_jpaFirst.metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class Produit {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(length=100)
	private String nom;
	private double prix;
	private double poid;
	
	public Produit(int id, String nom, double prix, double poid) {
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.poid = poid;
	}
	
}
