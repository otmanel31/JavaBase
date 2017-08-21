package com.otmanel.springExo1.beans;

public class Trompette implements IInstrument {

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
		System.out.println("je suis une trompette poet poet");
	}

}
