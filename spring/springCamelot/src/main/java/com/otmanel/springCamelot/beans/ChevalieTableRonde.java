package com.otmanel.springCamelot.beans;

public class ChevalieTableRonde implements IChevalier {

	private String nom;
	private double competence;
	private IQuete quete;
	private Cheval monture;
	
	public Cheval getMonture() {return this.monture;}
	public void setMonture(Cheval monture) {this.monture = monture;}
	public double getCompetence() {return competence;}
	public void setCompetence(double competence) {this.competence = competence;}
	public IQuete getQuete() {return quete;}
	public void setQuete(IQuete quete) {this.quete = quete;}
	public void setNom(String nom) {this.nom = nom;}

	public ChevalieTableRonde(String nom) {
		this.nom = nom;
	}
	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}

	@Override
	public void partirEnQuete() {
		System.out.println("moi, Sir "+getNom() + " part en quete " + getQuete() + " sur mon fidele destrier "
				+getMonture().getNom());
		boolean success = getQuete().realiserQuete(getCompetence());
		if( success ) System.out.println("moi " + getNom() + " revient glorieusement de la quete");
		else System.out.println("moi " + getNom() + " a eu un contre tps");
	}
	

}
