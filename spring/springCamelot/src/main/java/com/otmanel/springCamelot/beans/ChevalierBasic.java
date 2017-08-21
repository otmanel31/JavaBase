package com.otmanel.springCamelot.beans;

public class ChevalierBasic implements IChevalier {

	private String nom;
	private double competence;
	private IQuete quete;
	
	public double getCompetence() {return competence;}
	public void setCompetence(double competence) {this.competence = competence;}
	public IQuete getQuete() {return quete;}
	public void setQuete(IQuete quete) {this.quete = quete;}
	public void setNom(String nom) {this.nom = nom;}

	public ChevalierBasic(String nom) {
		this.nom = nom;
	}
	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}

	@Override
	public void partirEnQuete() {
		System.out.println("moi "+getNom() + " part en quete " + getQuete());
		boolean success = getQuete().realiserQuete(getCompetence());
		if( success ) System.out.println("moi " + getNom() + " revient victorieusement de la quete");
		else System.out.println("moi " + getNom() + " aura plus de chance une autre fois");
	}
	@Override
	public String toString() {
		return "CbevalierBasic [nom=" + nom + ", competence=" + competence + ", quete=" + quete + "]";
	}

}
