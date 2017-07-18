package exo_thread;

import java.io.File;
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
		
	}
	public void logEvent() {
		
	}
	
	@Override
	public String toString() {
		return "RepertoireEvents [fileName=" + fileName + ", date=" + date ;
	}
	
}
