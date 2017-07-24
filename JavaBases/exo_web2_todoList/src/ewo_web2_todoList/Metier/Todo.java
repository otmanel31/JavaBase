package ewo_web2_todoList.Metier;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Todo {
	private int id;
	private String description;
	private int priorite;
	private String contexte;
	private boolean finished;
	private LocalDateTime dateCreation;
	private String dateToStr;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	public int getPriorite() {return priorite;}
	public void setPriorite(int priorite) {this.priorite = priorite;}
	
	public String getContexte() {return contexte;}
	public void setContexte(String contexte) {this.contexte = contexte;}
	
	public boolean isFinished() {return finished;}
	public void setFinished(boolean finsished) {this.finished = finsished;}
	
	public LocalDateTime getDateCreation() {return dateCreation;}
	public void setDateCreation(LocalDateTime dateCreation) {this.dateCreation = dateCreation;}
	
	public String getDateToStr() {
		/*SimpleDateFormat formater = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		System.out.println(getDateCreation());
		dateToStr = formater.format(getDateCreation());*/
		//System.out.println(dateToStr);
		
		DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm:ss").toFormatter();
		return getDateCreation().format(df);

	}
	public void setDateToStr(String date) {
		//DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		date = date.replace(" ", "T");
		LocalDateTime datetime = LocalDateTime.parse(date ,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		setDateCreation(datetime);
	}
	
	public Todo(int id, String description, int priorite, String contexte, 
			boolean finsished,LocalDateTime dateCreation) {
		setId(id);
		setDescription(description);
		setPriorite(priorite);
		setContexte(contexte);
		setFinished(finsished);
		setDateCreation(dateCreation);
	}
	
	public Todo() {
		this(0,"text..", 1, "text ...", false, LocalDateTime.now());
	}
	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", priorite=" + priorite + ", contexte=" + contexte
				+ ", finsished=" + finished + ", dateCreation=" + dateCreation + "]";
	}
	
	
	
	
	
	
}
