package com.otmanel.springCamelot.beans;

import java.util.Random;

public class QueteEpique implements IQuete {

	private String description;
	private double difficulte;
	private Random rd;
	
	public void setDescription(String description) {this.description = description;}
	public double getDifficulte() {return difficulte;}
	public void setDifficulte(double difficulte) {this.difficulte = difficulte;}
	public  QueteEpique() {
		rd = new Random();
	}
	
	@Override
	public String getDescrition() {
		return description;
	}
	@Override
	public boolean realiserQuete(double competence) {
		return  ((rd.nextDouble() * competence) > difficulte) 
				&&
				((rd.nextDouble() * competence) > difficulte) 
				;
	}
	@Override
	public String toString() {
		return "QueteBasic [description=" + description + ", difficulte=" + difficulte + "]";
	}

}
