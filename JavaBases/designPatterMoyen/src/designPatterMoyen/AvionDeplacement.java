package designPatterMoyen;

public class AvionDeplacement implements DeplacementFactory {

	private String depart;
	private String arrivee;
	private int distance;
	
	@Override
	public String getDepart() {
		return depart;
	}

	@Override
	public String getArrivee() {
		return arrivee;
	}

	@Override
	public int getDuree() {
		return (int)Math.round((distance / 800.0)*60);
	}

	public AvionDeplacement(String depart, String arrivee, int distance) {
		super();
		this.depart = depart;
		this.arrivee = arrivee;
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "AvionDeplacement [depart=" + depart + ", arrivee=" + arrivee + ", distance=" + distance + "]";
	}
	

}
