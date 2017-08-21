package com.otmanel.springExo1.beans;

public class Violon implements IInstrument {

	private String nom;
	@Override
	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public void jouer() {
		System.out.println("je jouuuuuee du violon vizivizivizviziivziivz");
	}

}
