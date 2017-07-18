package exo_thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Date;

public class RepertoireEvents {
	private String fileName;
	private Date date;
	private Object verrou;
	
	public String getFileName() {return fileName;}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getDate() {return date;}
	public void setDate(Date date) {
		this.date = date;
	}

	
	public RepertoireEvents(String fileName, Date date) {
		super();
		setFileName(fileName);
		setDate(date);
		this.verrou = new Object();
	}
	
	public void addEvent(String f, Date date) {
		File file = new File("Events.txt");
		try {
			//RandomAccessFile writer = new RandomAccessFile(file, "rw");
			FileWriter writer = new FileWriter(file, true);
			PrintWriter writer2 = new PrintWriter(writer);
			writer2.println("changement detecté: " + f + "; Date: " + date);
			writer2.close();
			writer.close();
		}catch(IOException e) {e.printStackTrace();}
		
	}

	
	@Override
	public String toString() {
		return "RepertoireEvents [fileName=" + fileName + ", date=" + date ;
	}
	
}
