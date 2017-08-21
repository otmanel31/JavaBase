package com.otmanel.jpa_exo2__cms.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor  @ToString(exclude= {"contents"})
public class Tag {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id 									private int id;
											private String libelle;
	@ManyToMany(mappedBy="tags")			private Set<Content> contents;
	
	public Tag(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	public Set<Content> getContents(){
		if (this.contents == null ) this.contents = new HashSet<>();
		return this.contents;
	}
}
