package exo_thread;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WorkerEvent implements Runnable{

	private String name;
	private String path;
	private int size;
	
	
	public String getName() {return name;}
	public void setName(String name) {
		this.name = name;
	}
	
	public WorkerEvent(String name, String path) {
		super();
		setName(name);
		this.path  = path;
	}
	
	@Override
	public void run() {
		boolean continuer = true;
		String[] oldFiles = new File(this.path).list();
		System.out.println("demarrage surveillance !! " + getName());
		while (continuer) {
			
			String[] listFiles = new File(this.path).list();
			final String[] toFind = oldFiles;
			//List<String> changement = 
			Arrays.stream(listFiles)
				  .filter(f -> !(Arrays.stream(toFind).anyMatch(f2 -> f.equals(f2))))
				  .forEach( f -> System.out.println(f));
			if (listFiles.length < oldFiles.length) System.out.println( "un fichier a été supprimé!" );
				  //.collect(Collectors.toList());
			/*if (changement.size() > 0 ) 
				changement.stream().forEach(f-> System.out.println("changement detecté " + f));*/
			oldFiles = listFiles;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
		}
		
		
		
	}
	
}
