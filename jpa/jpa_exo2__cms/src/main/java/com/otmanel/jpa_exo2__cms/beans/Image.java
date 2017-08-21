package com.otmanel.jpa_exo2__cms.beans;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(callSuper=true, exclude= {"galerie"})
public class Image extends Content{
					private String filename;
					private String typeimage;
	@ManyToOne 		private Galerie galerie;

	public Image(int id, String nom, LocalDateTime creation, LocalDateTime edition, String filename, String typeimage) {
		super(id, nom, creation, edition);
		this.filename = filename;
		this.typeimage = typeimage;
	}
	
	
}
