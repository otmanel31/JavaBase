package exo_swing.metier;

import java.awt.color.CMMException;
import java.util.Comparator;
import java.util.function.Predicate;

public class Contact {
	
	// generateur de lambda
	public static Predicate<Contact> getFilter(final String referent){
		if (referent.equals("all")) return p -> true;
		return c -> c.getReferent().equals(referent);
	}
	public static Predicate<Contact> getGold(final boolean all){
		
		return c->  all || c.isGold() ;
	}
	public static Predicate<Contact> filterAge(final int age){
		return c-> c.getAge() > age;
	}
	
	public static final Comparator<Contact> ID_SORT = (c1,c2)->Integer.compare(c1.getId(), c2.getId());
	public static final Comparator<Contact> NOM_SORT = (c1, c2)-> c1.getNom().compareTo(c2.getNom());
	public static final Comparator<Contact> PRENOM_SORT = (c1, c2)-> c1.getPrenom().compareTo(c2.getPrenom());
	public static final Comparator<Contact> EMAIL_SORT = (c1, c2)-> c1.getEmail().compareTo(c2.getEmail());
	public static final Comparator<Contact> AGE_SORT = (c1,c2)->Integer.compare(c1.getAge(), c2.getAge());
	//public static final Comparator<Contact> BOOLEAN_SORT = ;
	
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private int genre;
	private int age;
	private boolean isGold;
	private String referent;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getGenre() {return genre;}
	public void setGenre(int genre) {
		this.genre = genre;
	}
	public int getAge() {return age;}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isGold() {return isGold;}
	public void setGold(boolean isGold) {
		this.isGold = isGold;
	}
	public String getReferent() {return referent;
	}
	public void setReferent(String referent) {
		this.referent = referent;
	}
	public Contact(int id, String nom, String prenom, String email, int genre, int age, boolean isGold,
			String referent) {
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setEmail(email);
		setGenre(genre);
		setAge(age);
		setGold(isGold);
		setReferent(referent);
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", genre=" + genre
				+ ", age=" + age + ", isGold=" + isGold + ", referent=" + referent + "]";
	}
	
}
