package exo_thread_prof;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		// creation des espions via le gestionnaire despions
		SpyManager manager = new SpyManager("config.txt");
		manager.startSpying();
		System.out.println("appuyer sur entree ....");
		Scanner reader = new Scanner(System.in);
		reader.nextLine();
		System.out.println("arret demander ");
		manager.stopSpying();
		manager.saveEvent("events.log");
	}

}
