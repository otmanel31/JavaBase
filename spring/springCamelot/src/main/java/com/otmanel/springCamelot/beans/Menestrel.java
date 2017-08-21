package com.otmanel.springCamelot.beans;

public class Menestrel {
	public void chanterAvant(IChevalier chevalier) {
		System.out.println("Tralalalalal Sir " + chevalier.getNom() + 
				" part courageusement en quete");
	}
	
	public void chanterApres(IChevalier chevalier) {
		System.out.println("Tralalalalal Sir " + chevalier.getNom() + 
				" revient joyeusement de quete");
	}
}
