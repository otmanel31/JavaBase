package com.otmanel.springEventExpression.beans;

import java.beans.PropertyEditorSupport;

public class AdresseEditor extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		System.out.println("conversion de adresse vers texte");
		// lasdresse a converttir zn texte est dej	 dispo via getValue (prealablement preparer par sring)
		Adresse a = (Adresse)getValue();
		StringBuilder sb = new StringBuilder();
		sb.append(a.getRue())
			.append(';').append(a.getVille())
			.append(';').append(a.getCodePostal())
			.append(';').append(a.getPays())
		;
		
		return sb.toString();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.isEmpty()) throw new IllegalArgumentException("Adresse incorrecte - null || empty");
		//decoupage
		String[] champs = text.split(";");
		if (champs.length != 4) throw new IllegalArgumentException("adresse incorecte - format invalide");
		System.out.println("conversion de texte vers adresse");
		setValue(new Adresse(champs[0], champs[1], champs[2], champs[3]));
	}
	
}
