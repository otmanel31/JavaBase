package exo_base3_oop.metier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Article {
	private int id;
	private String titre;
	private String corp;
	private String nomAuteur;
	private double rating;
	
	// FAIRE CONST LONGEUR DES MES ATTRIUBUTS ET CHANGER DANS LES GETS / SETS
	
	private static int compteur = 0;
	
	private static final String DEFAULT_TITLE; //= "LOREM";
	private static final String DEFAULT_CORP; // = "Lorem Ipsum";
	private static final String DEFAULT_NAME; // = "noNBame";
	private static final double DEFAULT_RATING; // = 2.5;

	static {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {e.printStackTrace();} 
		DEFAULT_TITLE = prop.getProperty("titre_default", "absent");
		DEFAULT_CORP = prop.getProperty("corp_defaut", "absent");
		DEFAULT_NAME = prop.getProperty("auteur", "absent");
		DEFAULT_RATING = Double.parseDouble(prop.getProperty("rating", "2.5"));
	}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		if (titre != null && titre.length() >= 5 && titre.length() <= 100) this.titre = titre;
		else this.titre = DEFAULT_TITLE;
	}
	
	public String getCorp() {return corp;}
	public void setCorp(String corp) {
		if (corp != null && corp.length() >=5 && corp.length() <= 400) this.corp = corp;
		else this.corp = DEFAULT_CORP;
	}
	
	public String getNomAuteur() {return nomAuteur;}
	public void setNomAuteur(String nomAuteur) {
		if( nomAuteur != null && nomAuteur.length() >=2 || nomAuteur.length() <= 50) this.nomAuteur = nomAuteur;
		else this.nomAuteur = DEFAULT_NAME;
	}
	
	public double getRating() {return rating;}
	public void setRating(double rating) {
		if ( rating>=0.0 && rating <=5.0 ) this.rating = rating;
		else this.rating = DEFAULT_RATING;
	}
	
	public Article() {
		this(DEFAULT_TITLE, DEFAULT_CORP, DEFAULT_NAME, DEFAULT_RATING );
	}
	public Article( String titre, String corp, String nomAuteur, double rating) {
		// ERREUR DORENAVANT PASSER PAR GET ET SET POUR LES CONTROLES EVENTUEL
		// NON PRIS EN CHARGE SUR ATTR PRIV DIRECTEMENT
		super();
		this.id = compteur;
		this.titre = titre;
		this.corp = corp;
		this.nomAuteur = nomAuteur;
		this.setRating(rating);;
		compteur++;
	}
	public String toString() {
		return "Article[ id:"+this.id+" titre: "+ this.titre+" corp: "+this.corp+" auteur: "+this.nomAuteur+
				" vote: "+this.rating+"]";
	}
}
