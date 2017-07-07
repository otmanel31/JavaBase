package metiers;

public abstract class Aninal {

	private int age;
	private String nom; 
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public abstract void bouger();
	public abstract void crier();
}
