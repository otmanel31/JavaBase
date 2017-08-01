package com.otmanel.eox_struts_2_voyages.metiers;

public class Agence {
	private int id;
	private String nom;
	private String email;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	public Agence() {}
	public Agence(int id, String nom, String email) {
		this.id = id;
		this.nom = nom;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Agence [id=" + id + ", nom=" + nom + ", email=" + email + "]";
	}	
}
