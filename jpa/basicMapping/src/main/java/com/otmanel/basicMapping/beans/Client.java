package com.otmanel.basicMapping.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
	/*
	 * On peux annoter sur attribbut ou sur un  getter
	 * Attention on ne peux melanger ds une entité les 2 
	 * Le choix determine concretement si hibernate  passe par les accesseur ou
	 * directement par les attributs
	 * 	 Le cjoix est dicté par l'attribut Id 
	 * On annote pour jap/ hibernate jamais les setters 
	 */
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="patronyme")
	private String nom;
	private String prenom;
	private String email;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public Client() {}
	public Client(int id, String nom, String prenom, String email) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}
	
	
}
