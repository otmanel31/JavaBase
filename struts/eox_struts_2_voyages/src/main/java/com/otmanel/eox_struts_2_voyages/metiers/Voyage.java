package com.otmanel.eox_struts_2_voyages.metiers;

import java.util.Date;

public class Voyage {
	private int id;
	private String libelle;
	private String destination;
	private Date dateDept;
	private Date dateRetour;
	private double prix;
	private int agence;
	private boolean passport;
	private Agence agencee;
	
	public Agence getAgencee() {return this.agencee;};
	public void setAgencee(Agence a) {this.agencee = a;} 
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getLibelle() {return libelle;}
	public void setLibelle(String libelle) {this.libelle = libelle;}
	public String getDestination() {return destination;}
	public void setDestination(String destination) {this.destination = destination;}
	public Date getDateDept() {return dateDept;}
	public void setDateDept(Date dateDept) {this.dateDept = dateDept;}
	public Date getDateRetour() {return dateRetour;}
	public void setDateRetour(Date dateRetour) {this.dateRetour = dateRetour;}
	public double getPrix() {return prix;}
	public void setPrix(double prix) {this.prix = prix;}
	public int getAgence() {return agence;}
	public void setAgence(int agence) {this.agence = agence;}
	public boolean isPassport() {return passport;}
	public void setPassport(boolean passport) {this.passport = passport;}
	
	public Voyage(int id, String libelle, String destination, Date dateDept, Date dateRetour, double prix,
			int agence, boolean passport) {
		this.id = id;
		this.libelle = libelle;
		this.destination = destination;
		this.dateDept = dateDept;
		this.dateRetour = dateRetour;
		this.prix = prix;
		this.agence = agence;
		this.passport = passport;
		this.agencee = null;
	}
	public Voyage() {}
	
	@Override
	public String toString() {
		return "Voyage [id=" + id + ", libelle=" + libelle + ", destination=" + destination + ", dateDept=" + dateDept
				+ ", dateRetour=" + dateRetour + ", prix=" + prix + ", agence=" + agence + ", passport=" + passport
				+ "]";
	}
	
	public void copyFrom(Voyage other) {
		setId(other.getId());
		setLibelle(other.getLibelle());
		setDateDept(other.getDateDept());
		setDateRetour(other.getDateRetour());
		setAgence(other.getAgence());
		setDestination(other.getDestination());
		setPrix(other.getPrix());
		setPassport(other.isPassport());
	}
	
}
