package com.otmanel.exo1_jpa.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor 
@ToString(exclude="listCours")
public class Matiere {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String libelle;
	@OneToMany(mappedBy="matiere")
	private Set<Cours> listCours;
	
	public Set<Cours> getListCours() {
		if (listCours == null) listCours = new HashSet<>();
		return listCours;
	}
	
	public Matiere(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}
	
}
