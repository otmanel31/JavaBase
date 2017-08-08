package com.otmanel.jpaInclusion.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.otmanel.jpaInclusion.beans.Geolocalisation;
/*
 * cette classe serivra a hibernate pour convertiur les objets
 * geolocalisation en chaine de caractere et inversement de convertir
 * geoloc (dans entité) -> string (dans colonne) quand sauvegarde
 */
//@Converter(autoApply=true) // sapplique a tt les objets localisation
@Converter
public class LocalisationConverter implements AttributeConverter<Geolocalisation, String> {

	@Override
	public String convertToDatabaseColumn(Geolocalisation attribute) {
		if (attribute == null)
			return null;
		return attribute.getLongitude() + ";" + attribute.getLatitude();
	}

	@Override
	public Geolocalisation convertToEntityAttribute(String dbData) {
		if (dbData == null || dbData.isEmpty()) 
			return null;
		String[] champs = dbData.split(";");
		if (champs.length != 2) 
			throw new IllegalArgumentException("Format de localisation nn valide");
		return new Geolocalisation(Double.parseDouble(champs[0]), Double.parseDouble(champs[1]));
	}

}
