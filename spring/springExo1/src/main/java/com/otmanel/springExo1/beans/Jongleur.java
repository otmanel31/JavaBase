package com.otmanel.springExo1.beans;

public class Jongleur implements IArtitste {
	
	private String nom;
	private IInstrument instrument;
	private int nbBalle;
	
	
	public Jongleur(String nom, int nbBalle) {
		super();
		System.out.println("instantiation du jongleur");
		this.nom = nom;
		this.nbBalle = nbBalle;
	}
	
	public int getNbBalle() {return nbBalle;}
	public void setNbBalle(int nbBalle) {this.nbBalle = nbBalle;}
	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public void faireUnNumero() {
		System.out.println("moi, " + this.getNom() +" vai jongler avec " + this.nbBalle + " balles") ;
	}

	@Override
	public IInstrument getInstrument() {
		return this.instrument;
	}

}
