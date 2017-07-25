package designPatternFin;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector.Characteristics;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Program {
	public static void main(String[] args) {
		// pattern iterator
		// -> principe de puvoir parcourir des colection et effectuer des taitement essus
		String[] pays = {"maroc", "italie", "espagne", "france", "allemagne"};
		
		List<String> mangas = Arrays.asList("Death note", "naruto", "dbz", "one peace", "bleach");
		
		// Iterable == interface
		Iterable<String> it1 = mangas;
		// recuperation du curseur
		Iterator<String> curseur = it1.iterator();
		// parcourir tant que le curseur nest pas a la fin
		while(curseur.hasNext()) {
			System.out.println(curseur.next());
		}
		
		// iterator sur des jours
		PeriodeTps p1 = new PeriodeTps(LocalDate.of(2017, 6, 26), LocalDate.now());
		for (LocalDate p: p1) {
			System.out.println(p);
		}
		System.out.println(" ---------------------------------------");
		
		JoursSupplier js1 = new JoursSupplier(LocalDate.now());
		Stream.generate(js1)
			.filter(j->j.getDayOfWeek().equals(DayOfWeek.SUNDAY))
			.limit(20).forEach(j->System.out.println(j));
		System.out.println(" ---------------------------------------");
		// trasnformatiob de l'iterable en stream
		StreamSupport.stream(p1.spliterator(), false).filter(j->j.getDayOfWeek().equals(DayOfWeek.SUNDAY))
			.forEach(j->System.out.println(j));
	}
}
