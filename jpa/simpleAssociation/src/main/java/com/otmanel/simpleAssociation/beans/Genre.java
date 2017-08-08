package com.otmanel.simpleAssociation.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

@Entity
public class Genre {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String libelle;
	@OneToMany(mappedBy="genre" /*, cascade=CascadeType.REMOVE*/) // ajouter nom attribut en face ds film pour avoir une symettie
	// puis  le cascade type attention => si on suprime in genre de film il suppr le film associé == DANGER
	// la cascade propage une operation vers le objets associés via vcettte assocaition
	private Set<Film> films;
	
	@PreRemove
	public void cleanFilmBeforeRemove() {
		for(Film f : getFilms()) {
			f.setGenre(null);
		}
		getFilms().clear();
	}
	
	public Set<Film> getFilms() {
		if (films == null) films = new HashSet<>(); 
		return films;
	}
	public void setFilms(Set<Film> films) {this.films = films;}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getLibelle() {return libelle;}
	public void setLibelle(String libelle) {this.libelle = libelle;}
	
	public Genre() {}
	public Genre(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}
	@Override
	public String toString() {
		return "Genre [id=" + id + ", libelle=" + libelle + "]";
	}
	
	
}
