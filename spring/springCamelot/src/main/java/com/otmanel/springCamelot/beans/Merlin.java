package com.otmanel.springCamelot.beans;

import java.util.List;
import java.util.Random;

public class Merlin {
	private List<String> actions;
	private List<String> sujets;
	private List<String> qualificatifs;
	private Random rd = new Random();
	public void setActions(List<String> actions) {this.actions = actions;}
	public void setSujets(List<String> sujets) {this.sujets = sujets;}
	public void setQualificatifs(List<String> qualificatifs) {this.qualificatifs = qualificatifs;}
	
	public IQuete genererQuete() {
		String description = this.actions.get(rd.nextInt(actions.size())) 
				+ " " + this.sujets.get(rd.nextInt(sujets.size())) 
				+ " " + this.qualificatifs.get(rd.nextInt(qualificatifs.size())) 
		;

		if (rd.nextBoolean()) {
			QueteBasic qb = new QueteBasic();
			qb.setDifficulte(rd.nextDouble() * 10.0);
			qb.setDescription(description);
			return qb;
		}
		else {
			QueteEpique qe = new QueteEpique();
			qe.setDifficulte(rd.nextDouble() * 10.0);
			qe.setDescription(description);
			return qe;
		}
	}
}
