package com.otmanel.jpaTablePerClass.beans;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(callSuper=true)
public class Employe extends Personne {
	private String poste;
	private double salaire;
	
	public Employe(int id, String nom, String prneom, String poste, double salaire) {
		super(id, nom, prneom);
		this.poste = poste;
		this.salaire = salaire;
	}
}
