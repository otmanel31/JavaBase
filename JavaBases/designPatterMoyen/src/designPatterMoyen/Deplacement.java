package designPatterMoyen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deplacement {
	Map<String, Double >  distance;
	
	public Deplacement() {
		distance = new HashMap<>();
		distance.put("paris:londres", 400.50);
		distance.put("paris:berlin", 1250.50);
		distance.put("bordeaux:madrid", 600.50);
		distance.put("paris:madrid", 1275.3);
		distance.put("paris:marrakech", 2542.3);
		distance.put("paris:dakkar", 5208.3);
		distance.put("paris:tokyo", 9600.3);
	}
	// methode favbrique de deplacement
	public DeplacementFactory builDeplacement(String depart,String arrivee) {
		String cle1 = depart + ":" + arrivee;
		String cle2 = arrivee + ":" + depart;
		double d = 0.0;
		if (distance.containsKey(cle1)) {
			d = distance.get(cle1);
		}else if (distance.containsKey(cle2)) {
			d = distance.get(cle2);
		}else {
			throw new IllegalArgumentException("Trajet inconnu");
		}
		if (d > 600) return new AvionDeplacement(depart, arrivee, (int)d);
		else return new TrainDeplacement(depart, arrivee, (int)d);
	}
}
