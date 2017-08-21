package com.otmanel.springExo1.beans;

import java.util.List;

public class HommeOrchestre implements IArtitste {
	private String nom;
	private List<IInstrument> instruments;
	
	public List<IInstrument> getInstruments() {return instruments;}
	public void setInstruments(List<IInstrument> instruments) {this.instruments = instruments;}
	public void setNom(String nom) {this.nom = nom;}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return this.nom;
	}

	@Override
	public void faireUnNumero() {
		
		System.out.println("bonjour a ts, moi l'homme orchestre repondant du nom " + getNom() +
				" vai vous éblouir ... ");
		for (IInstrument i : this.instruments) i.jouer();

	}

	@Override
	public IInstrument getInstrument() {
		return null;
	}

}
