package com.otmanel.exo1_jpa.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Cours {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String libelle;
	private Date dateDebut;
	private Date dateFin;
	private int capaciteMax;
	@ManyToMany(mappedBy="listCours")
	private Set<Etudiant> etudiants;
	@ManyToOne
	private Professeur prof;
	@ManyToOne
	private Matiere matiere;
	
	
	
	public Matiere getMatiere() {return matiere;}
	public void setMatiere(Matiere matiere) {this.matiere = matiere;}
	public Professeur getProf() {
		return prof;
	}
	public void setProf(Professeur prof) {this.prof = prof;}
	public Set<Etudiant> getEtudiants() {
		if (etudiants == null) etudiants = new HashSet<>();
		return etudiants;
	}
	public void setEtudiants(Set<Etudiant> etudiants) {this.etudiants = etudiants;}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getLibelle() {return libelle;}
	public void setLibelle(String libelle) {this.libelle = libelle;}
	public Date getDateDebut() {return dateDebut;}
	public void setDateDebut(Date dateDebut) {this.dateDebut = dateDebut;}
	public Date getDateFin() {return dateFin;}
	public void setDateFin(Date dateFin) {this.dateFin = dateFin;}
	public int getCapaciteMax() {return capaciteMax;}
	public void setCapaciteMax(int capaciteMax) {this.capaciteMax = capaciteMax;}
	
	public Cours() {}
	public Cours(int id, String libelle, Date dateDebut, Date dateFin, int capaciteMax) {
		this.id = id;
		this.libelle = libelle;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.capaciteMax = capaciteMax;
	}
	@Override
	public String toString() {
		return "Cours [id=" + id + ", libelle=" + libelle + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", capaciteMax=" + capaciteMax + "]";
	}
}
