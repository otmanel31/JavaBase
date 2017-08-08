package com.otmanel.jpaTablePerClass.beans;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(callSuper=true)
public class Client extends Personne {
	private String email;
	private double soldeCompte;
	
	public Client(int id, String nom, String prneom, String email, double soldeCompte) {
		super(id, nom, prneom);
		this.email = email;
		this.soldeCompte = soldeCompte;
	}
	

}
