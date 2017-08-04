package com.otmanel.exo1_jpa.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
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
	public void setListCours(Set<Cours> listCours) {
		this.listCours = listCours;
	}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getLibelle() {return libelle;}
	public void setLibelle(String libelle) {this.libelle = libelle;}
	
	public Matiere() {}
	public Matiere(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}
	@Override
	public String toString() {
		return "Matiere [id=" + id + ", libelle=" + libelle + "]";
	}
}
