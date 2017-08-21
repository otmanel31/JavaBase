package com.otmanel.springEventExpression.beans;

public class IdGenerator {
	private int compteur;
	
	public IdGenerator(int valeurInitiale) {
		this.compteur = valeurInitiale;
	}
	
	public int nextId() {
		return compteur++;
	}
}
