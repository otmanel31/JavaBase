package exo_thread_prof;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SpyManager {
	public static class spyEvent{
		// ou directoRyEvent
		private String source;
		private String message;
		private LocalDateTime date;
		
		public String getSource() {return source;}
		public void setSource(String source) {
			this.source = source;
		}
		public String getMessage() {return message;}
		public void setMessage(String message) {
			this.message = message;
		}
		public LocalDateTime getDate() {return date;}
		public void setDate(LocalDateTime date) {
			this.date = date;
		}
		public spyEvent(String source, String message, LocalDateTime date) {
			super();
			this.source = source;
			this.message = message;
			this.date = date;
		}
		@Override
		public String toString() {
			return "spyEvent [source=" + source + ", message=" + message + ", date=" + date + "]";
		}
		
	}
	// mes espions
	List<DirectorySpy> spies;
	// le threadpool pour faire tournee mes espiuons
	private ExecutorService workers;
	// ma liste devenements
	private List<spyEvent> events;

	public SpyManager(String configFileName) {
		spies = new ArrayList<>();
		events = new ArrayList<>();
		
		File config = new File(configFileName);
		try {
			Scanner reader = new Scanner(config);
			while(reader.hasNextLine()) {
				// un non de repertoire a surveiller
				String dirName = reader.nextLine();
				// pour chaque rep je creer un objet directory path et je garde en memeoire ss demarrer pour le moment
				DirectorySpy ds = new DirectorySpy(dirName, this);
				spies.add(ds);
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// je fais le nb workers mais je ne les demerra pas de suite 
		// on dimenssionne la threadpool en fonction du nombre espions/repertoire a gerer
		
		workers = Executors.newFixedThreadPool(spies.size());
	}
	// publication d'un evenement en provbenance dun des espions
	// syncronysé pour eviter des conflits a lajout ds la liste, en efffet tt les directory 
	// spy tournant en paralllele peuvent apeler a tt moment cette methode pour signaler un evenement
	public synchronized void addEvent(String source, String message) {
		spyEvent s= new spyEvent(source, message, LocalDateTime.now());
		events.add(s);
		//System.out.println(events);
	}
	public void startSpying() {
		// j ajoute mes spies comme workers ce qui les demarre ds la thread pool
		for (DirectorySpy ds : spies) {
			workers.submit(ds);
		}
	}
	public void stopSpying() {
		workers.shutdown();
		for(DirectorySpy ds: spies) {
			ds.setMustStop(true);
		}
		try {
			workers.awaitTermination(10, TimeUnit.SECONDS);
			if (workers.isTerminated()) System.out.println("shutDown competed ");
			else System.out.println("Problem at shutdown");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void saveEvent(String fileName) {
		try {
			// ouvrir fichier en ecriture
			PrintWriter pw  = new PrintWriter(fileName);
			// ecrire un event a chaque ligne
			events.stream().forEach(f -> pw.println(f.toString()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
