package com.otmanel.exo_struts_jpa_spring.metier;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(exclude= {"acteurs"})
public class Film {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String titre;
	private String synopsis;
	private int annee;
	@ManyToMany
	private Set<Acteur> acteurs;
	@ManyToOne
	private Realisateur realisateur;
	@Transient
	private int realisateurId;
	
	@PostLoad
	public void fillRealisateurId() {
		if (getRealisateur() != null)
			setRealisateurId(getRealisateur().getId());
	}
}
