package designPatterMoyen;

public class TrainDeplacement implements DeplacementFactory {

	private String depart;
	private String arrivee;
	private int distance;
	
	public TrainDeplacement(String depart, String arrivee, int distance) {
		super();
		this.depart = depart;
		this.arrivee = arrivee;
		this.distance = distance;
	}

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
		return (int)Math.round((distance / 100.0)*60);
	}

	@Override
	public String toString() {
		return "TrainDeplacement [depart=" + depart + ", arrivee=" + arrivee + ", distance=" + distance + "]";
	}
	
}
