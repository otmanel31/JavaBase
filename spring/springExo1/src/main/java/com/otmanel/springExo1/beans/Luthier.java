package com.otmanel.springExo1.beans;

import java.util.List;
import java.util.Random;

public class Luthier {
	private String instrument;
	private List<String> nomInstrument;
	private Random rd = new Random();
	
	public String getInstrument() {return instrument;}
	public void setInstrument(String instrument) {this.instrument = instrument;}
	public List<String> getNomInstrument() {return nomInstrument;}
	public void setNomInstrument(List<String> nomInstrument) {this.nomInstrument = nomInstrument;}

	public IInstrument genererInstrument() {
		String nom = this.nomInstrument.get(rd.nextInt(nomInstrument.size()));
		
		Violon v = new Violon();
		v.setNom(nom);
		return v;
	}
}
