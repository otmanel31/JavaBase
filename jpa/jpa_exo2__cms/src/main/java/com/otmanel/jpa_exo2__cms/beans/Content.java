package com.otmanel.jpa_exo2__cms.beans;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import com.otmanel.jpa_exo2__cms.utils.IDateable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity //par defayut sin non precisé c du join et nn single table
@Inheritance(strategy=InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor  @ToString(exclude= {"tags"})
@EntityListeners(Dateur.class)
public class Content implements IDateable{
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id 					private int id;
	@Column(length=50)		private String nom;
							private LocalDateTime creation;
							private LocalDateTime edition;
	@ManyToMany				private Set<Tag> tags;
	
	public Content(int id, String nom, LocalDateTime creation, LocalDateTime edition) {
		super();
		this.id = id;
		this.nom = nom;
		this.creation = creation;
		this.edition = edition;
	}
	public Set<Tag> getTags(){
		if (this.tags == null ) this.tags = new HashSet<>();
		return this.tags;
	}
}
