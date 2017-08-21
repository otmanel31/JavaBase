package com.otmanel.springCamelot.beans;

import java.util.List;
import java.util.Random;

public class Ecurie {
	private List<Cheval> chevaux;
	private Random rd = new Random();
	
	public void setChevaux(List<Cheval> chevaux) {this.chevaux = chevaux;}
	
	public Cheval fournirChevaux() {
		if (chevaux.isEmpty())
			throw new RuntimeException("Plus de chevaux");
		return chevaux.remove(rd.nextInt(chevaux.size()));
	}
}
