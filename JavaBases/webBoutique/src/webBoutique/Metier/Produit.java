package webBoutique.Metier;

public class Produit {
	private int id;
	private String nom;
	private double prix;
	private double poid;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	
	public double getPrix() {return prix;}
	public void setPrix(double prix) {this.prix = prix;}
	
	public double getPoid() {return poid;}
	public void setPoid(double poid) {this.poid = poid;}
	
	public Produit(int id, String nom, double prix, double poid) {
		setId(id);
		setNom(nom);
		setPrix(prix);
		setPoid(poid);
	}
	public Produit() {
		this(0,"",0.0,0.0);
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", prix=" + prix + ", poid=" + poid + "]";
	}
}
