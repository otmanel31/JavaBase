package com.otmanel.springEventExpression.beans;


public class Client {
	private int id;
	private String nom;
	private String mail;
	private double solde;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getMail() {return mail;}
	public void setMail(String mail) {this.mail = mail;}
	public double getSolde() {return solde;}
	public void setSolde(double solde) {this.solde = solde;}
	
	public Client(int id, String nom, String mail, double solde) {
		this.id = id;
		this.nom = nom;
		this.mail = mail;
		this.solde = solde;
	}
	public Client() {}
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", mail=" + mail + ", solde=" + solde + "]";
	}
	
	
}
