package com.otmanel.struts2_spring_jpaFirst.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(exclude= {"categorie"})
public class Produit {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(length=100)
	private String nom;
	private double prix;
	private double poid;
	//@ManyToMany(mappedBy="produits", fetch=FetchType.EAGER) dangeeux car grosse requete
	@ManyToMany(mappedBy="produits")
	private Set<Categorie> categories;
	
	public Set<Categorie> getCategories(){
		if (categories == null) categories = new HashSet<>();
		return categories;
	}
	public Produit(int id, String nom, double prix, double poid) {
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.poid = poid;
	}
	
}
