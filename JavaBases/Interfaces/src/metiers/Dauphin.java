package metiers;

public class Dauphin extends Aninal{

	private double taille;
	
	public double getTaille() {return taille;}
	public void setTaille(double taille) {
		this.taille = taille;
	}

	@Override
	public void bouger() {
		System.out.println("nage nage bloubbloubllll");
	}

	@Override
	public void crier() {
		System.out.println("iiiiiiiiiiiiiiiiiihiiiiiiiiiiihiiiii");
	}
	@Override
	public String toString() {
		return "Dauphin [taille=" + taille + ", getAge()=" + getAge() + ", getNom()=" + getNom() + "]";
	}
	 
}
