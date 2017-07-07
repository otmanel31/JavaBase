package javaGenerique;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javaGenerique.metier.Tuple;

public class Program {
	public static void main(String[] args) {
		// tableau dynamique
		ArrayList tab1 = new ArrayList();
		tab1.add("hello world");
		tab1.add("Salam");
		
		System.out.println(tab1.size());
		for (Object str: tab1) {
			System.out.println(str);
		}
		// marche mais aucun controle de type // a éviter si possible
		// plutot utiliser les collections generique
		System.out.println("version avec array typéé  --------------------------- ");
		ArrayList<String> lundi = new ArrayList<>();
		lundi.add("lundi");
		lundi.add("monday");
		lundi.add("itnaye");
		lundi.add("montag");
		lundi.add("lunes");
		
		for (String str: lundi) {
			System.out.println(str + " " + str.length());
		}
		//  List en java est l'infterface commune des fonctionnalité de type Arraylist
		// arraylist est une classe concrete implementant l'interface liste
		List<String> listes = lundi;

		// Map est l'interface des collections de tyoe dictionnaire. unes des implementations existante
		// est la hashmap
		// une des limitations des ge,erique en java est qui ne marche qu'avec des classes !! 
		// Donc pas de int double ==> il faut utliser les versiions objets !! 
		Map<String, Integer> dico = new HashMap<>();
		dico.put("Paris", 10000000);
		dico.put("Hamburg", 1600000);
		dico.put("Tokyo", 14000000);
		
		System.out.println("nbre habitant tokyo " + dico.get("Tokyo"));
		System.out.println(dico.containsKey("Tokyo")); // return true
		// keyset retourne une collection de clé
		for ( String cle : dico.keySet()) {
			System.out.println("cle -> " + cle +" || value -> " + dico.get(cle));
		}
		
		Tuple<String, Double> couple1 = new Tuple<String, Double>("Paris", 37.2);
		if (couple1.getValue2() > 35) System.out.println("il commence a faire chaud !! ");
		
		ArrayList< Tuple<String, Integer>> meteoVille = new ArrayList<>();
		meteoVille.add(new Tuple<String, Integer>("Paris", 33));
		meteoVille.add(new Tuple<String, Integer>("New York", 21));
		meteoVille.add(new Tuple<String, Integer>("Djibouti", 38));
		meteoVille.add(new Tuple<String, Integer>("Oslo", 23));
		meteoVille.add(new Tuple<String, Integer>("Las Vegas", 35));
		
		System.out.println("------------------------------------------------");
		meteoVille.stream().forEach(t->System.out.println(t));
		System.out.println("Que les villes dont la t° est infereiur a 25° !");
		meteoVille.stream().filter(t->t.getValue2() < 25).forEach(t-> System.out.println(t));
		System.out.println("tri / sort ");
		// Collections.sort(meteoVille); pas possible car interface comparable non implementé 
		// sauf si on implemente Comparable voir classe tuple pour explication
		Collections.sort(meteoVille);
		


	}
} 
