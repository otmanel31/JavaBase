package com.otmanel.videoTech.metiers;

public class Film {
	private int id;
	private String title;
	private int annee;
	private String description;
	private String realisateur;
	private int rating;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public int getAnnee() {return annee;}
	public void setAnnee(int annee) {this.annee = annee;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public String getRealisateur() {return realisateur;}
	public void setRealisateur(String realisateur) {this.realisateur = realisateur;}
	public int getRating() {return rating;}
	public void setRating(int rating) {this.rating = rating;}
	
	public Film() {}
	
	public Film(int id, String title, int annee, String description, String realisateur, int rating) {
		this.id = id;
		this.title = title;
		this.annee = annee;
		this.description = description;
		this.realisateur = realisateur;
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", annee=" + annee + ", description=" + description
				+ ", realisateur=" + realisateur + ", rating=" + rating + "]";
	}
	
}
