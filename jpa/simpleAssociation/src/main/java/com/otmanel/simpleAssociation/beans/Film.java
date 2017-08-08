package com.otmanel.simpleAssociation.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Film {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String titre;
	@Temporal(TemporalType.DATE)
	private Date dateFilm;
	private int dureeMin;
	@ManyToMany //(fetch=FetchType.EAGER)
	private Set<Acteur> acteurs; // collection nn ordonnée
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Genre genre; // cle etrangerre tjr du coté many
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public Date getDateFilm() {return dateFilm;}
	public void setDateFilm(Date dateFilm) {this.dateFilm = dateFilm;}
	public int getDureeMin() {return dureeMin;}
	public void setDureeMin(int dureeMin) {this.dureeMin = dureeMin;}
	public Set<Acteur> getActeurs() {
		if (acteurs == null) acteurs = new HashSet<>();
		return acteurs;
	}
	public void setActeurs(Set<Acteur> acteurs) {this.acteurs = acteurs;}
	public Genre getGenre() {return genre;}
	public void setGenre(Genre genre) {this.genre = genre;}
	
	public Film() {}
	public Film(int id, String titre, Date dateFilm, int dureeMin) {
		this.id = id;
		this.titre = titre;
		this.dateFilm = dateFilm;
		this.dureeMin = dureeMin;
	}
	
	@Override
	public String toString() {
		return "Film [id=" + id + ", titre=" + titre + ", dateFilm=" + dateFilm + ", dureeMin=" + dureeMin + "]";
	}
	
	
}
