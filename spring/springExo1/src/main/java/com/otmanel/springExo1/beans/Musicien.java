package com.otmanel.springExo1.beans;

import java.util.Random;

public class Musicien implements IArtitste {
	private String nom;
	private IInstrument instrument;
	private Random rd = new Random();
	
	public IInstrument getInstrument() {return instrument;}
	public void setInstrument(IInstrument instrument) {this.instrument = instrument;}

	public void setNom(String nom) {this.nom = nom;}
	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return this.nom;
	}

	@Override
	public void faireUnNumero() {
		System.out.println("moi, " + getNom() + " vais vs jouer une instru avec " + getInstrument().getNom() +" !");
		instrument.jouer();
	}

}
