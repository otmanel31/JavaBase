package com.otmanel.springAdvancedAop.beans;

public class TextUtils implements ITextUtils {
	@Override
	public String inversion(String text) {
		System.out.println("dans la meth inversion: "  + text);
		if (text.length() > 100) throw new RuntimeException("Text trop trop long");
		StringBuilder sb = new StringBuilder(text.length());
		for (int i = text.length()-1; i>=0; i--) {
			sb.append(text.charAt(i));
		}
		return sb.toString();
	}
	@Override
	public int compterVoyelle(String text) {
		String voyelle = "aeiouy";
		int compteur= 0;
		for( int i= 0 ; i< text.length() -1; i++) {
			if(voyelle.indexOf(text.charAt(i)) != -1) compteur++;
		}
		return compteur;
	}
}
