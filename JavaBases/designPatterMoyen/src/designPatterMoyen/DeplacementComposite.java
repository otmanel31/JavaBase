package designPatterMoyen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeplacementComposite implements DeplacementFactory {

	private List<String> etapes;
	private List<DeplacementFactory> df;
	
	public DeplacementComposite(String ... etapes){
		this.etapes = Arrays.asList(etapes);
		Deplacement d = new Deplacement();
		String previousVille = null;
		for(String ville : this.etapes) {
			if (previousVille != null) {
				this.df.add(d.builDeplacement(previousVille, ville));
			}
		}
	}
	
	@Override
	public String getDepart() {
		return this.etapes.get(0);
	}

	@Override
	public String getArrivee() {
		return this.etapes.get(this.etapes.size() - 1);
	}

	// calcul duree total en accumulant ls duree des trajets intermedirare en composant
	// xes depplacement == design pattern composite
	@Override
	public int getDuree() {
		int total = 0;
		for(DeplacementFactory d: df) {
			total += d.getDuree();
		}
		return total;
	}

	@Override
	public String toString() {
		return "DeplacementComposite [etapes=" + etapes + ", df=" + df + "]";
	}

}
