package designPattern;
/*
 * mes objet crées sont immutable on ne peux changer la valeur une fois creer
 */

public class Adresse {
	private final String rue;
	private final String ville;
	private final String pays;
	
	public String getRue() {return rue;}
	public String getVille() {return ville;}
	public String getPays() {return pays;}
	
	public Adresse(String rue, String ville, String pays) {
		super();
		this.rue = rue;
		this.ville = ville;
		this.pays = pays;
	}
	
	public Adresse changeRue(String param) {
		return new Adresse(param, this.ville, this.pays);
	}
	
	@Override
	public String toString() {
		return "Adresse [rue=" + rue + ", ville=" + ville + ", pays=" + pays + "]";
	}
	
}
