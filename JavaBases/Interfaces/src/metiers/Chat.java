package metiers;

public class Chat extends Aninal{

	private String pellage;

	public String getPellage() {return pellage;}
	public void setPellage(String pellage) {
		this.pellage = pellage;
	}

	@Override
	public void bouger() {
		System.out.println("TELEPORTATIONNNNNNNNN ");
	}

	@Override
	public void crier() {
		System.out.println("miiaawwww   miawwwwww");
		
	}
	@Override
	public String toString() {
		return "Chat [pellage=" + pellage + ", getAge()=" + getAge() + ", getNom()=" + getNom() + "]";
	}
	
}
