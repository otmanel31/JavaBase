package exo_thread_prof;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DirectorySpy implements Runnable {

	private String directoryPath;
	private boolean mustStop;
	private SpyManager spyManager;
	
	
	public void setMustStop(boolean mustStop) {
		this.mustStop = mustStop;
	}

	public DirectorySpy(String directoryPath, SpyManager spyManager) {
		super();
		this.directoryPath = directoryPath;
		//this.mustStop = mustStop;
		this.spyManager = spyManager;
		setMustStop(false);
	}

	@Override
	public void run() {
		// on recupere dle repertoire en question
		File rep = new File(directoryPath);
		if (!rep.exists() || !rep.isDirectory()) {
			System.out.println("repertoire " + directoryPath + " non surveillable !!");
			return;
		}
		System.out.println("Debut de surveillance du repertoire " + directoryPath);

		// demande la liste de sfichier a linterieur
		List<String> oldFiles = Arrays.asList(rep.list());
		while(!mustStop) {
			// ceci dessous necessaire car dans le stram travailler unbiquement sur des variable finale
			final List<String> oldFilesTemp = oldFiles;
			List<String> newFiles = Arrays.asList(rep.list());
			newFiles.stream().filter( fName -> !oldFilesTemp.contains(fName))
				.forEach(fName ->{
					// ajouter au journal
					spyManager.addEvent(directoryPath, "nouveau fichier " + fName);
					System.out.println("Nouveau fichier "+ fName);
				});
			// puis linverse
			oldFiles.stream().filter(fName -> !newFiles.contains(fName))
				.forEach(fName ->{
					// ajout event au journal
					spyManager.addEvent(directoryPath, "fichier disparu " + fName);
					System.out.println("Fichier disparu " + fName);
				});
			// oldlist devient new list pour la nouvelle iteration
			oldFiles = newFiles;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Fin de surveillance du repertoire " + directoryPath);

	}

}
