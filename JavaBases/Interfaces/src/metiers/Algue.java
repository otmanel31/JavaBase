package metiers;

public class Algue extends Vegetal{

	private String couleur;

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public Algue(int age, String espece, String couleur) {
		super(age, espece);
		this.couleur = couleur;
	}

	@Override
	public void pousser() {
		System.out.println(getEspece() + " chhuuut je pousse ");
		
	}
	
	
}
