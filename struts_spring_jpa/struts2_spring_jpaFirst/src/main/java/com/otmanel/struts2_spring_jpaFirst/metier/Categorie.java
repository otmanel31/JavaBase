package com.otmanel.struts2_spring_jpaFirst.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ManyToAny;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(exclude= {"produits"})
public class Categorie {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(length=100, unique=true)
	private String libelle;
	@ManyToMany
	private Set<Produit> produits;
	
	public Set<Produit> getProduits(){
		if (produits == null) produits = new HashSet<>();
		return produits; 
	}
	public Categorie(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}
	
}
