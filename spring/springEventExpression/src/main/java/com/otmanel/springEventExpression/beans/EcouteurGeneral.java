package com.otmanel.springEventExpression.beans;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class EcouteurGeneral implements ApplicationListener<ApplicationEvent>{

	private String nom;
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}

	@Override
	public void onApplicationEvent(ApplicationEvent evt) {
		// cette methode sera appeler par spring quand un eveneme,nt du bon type
		// (applucation evenet) o dun rtype derrivé est déclenché ds le contexte spring
		System.out.println(getNom() + " a recu levenement " + evt);
	}
	
}
