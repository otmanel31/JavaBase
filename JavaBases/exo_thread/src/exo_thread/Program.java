package exo_thread;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program {

	public static void main(String[] args) {		
		
		//WorkerEvent w1 = new WorkerEvent("rep1", "C:\\Users\\Stagiaire\\dwhelper");
		WorkerEvent w2 = new WorkerEvent("rep2", "C:\\Users\\Stagiaire\\formation1");
		
		ExecutorService e1 = Executors.newFixedThreadPool(4);

		//e1.submit(w1);

		e1.submit(w2);
		
	}
	
	
}
