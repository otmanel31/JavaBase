package com.otmanel.simpleAssociation.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Acteur {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	// mappedBy dit cette association est le mirroir dune autre et dinc
	// pas besoin de joinuture
	@ManyToMany(mappedBy="acteurs")
	private Set<Film> films;
	
	
	public Set<Film> getFilms() {
		if (films == null) films = new HashSet<>();
		return films;
	}
	public void setFilms(Set<Film> films) {this.films = films;}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	
	public Acteur() {}
	public Acteur(int id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}
	@Override
	public String toString() {
		return "Acteur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
}
