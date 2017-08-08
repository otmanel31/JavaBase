package com.otmanel.jpa_exo2__cms.beans;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(callSuper=true)
public class DocumentPdf extends Content {
	private String titre;
	private String nomAuteur;
	
	public DocumentPdf(int id, String nom, LocalDateTime creation, LocalDateTime edition, String titre,
			String nomAuteur) {
		super(id, nom, creation, edition);
		this.titre = titre;
		this.nomAuteur = nomAuteur;
	}
	
}
