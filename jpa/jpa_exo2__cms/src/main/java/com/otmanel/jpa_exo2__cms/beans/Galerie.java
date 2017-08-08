package com.otmanel.jpa_exo2__cms.beans;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(callSuper=true, exclude = {"images"})
public class Galerie extends Content {
										private String titre;
	@OneToMany(mappedBy="galerie")		private Set<Image> images;
	
	public Galerie(int id, String nom, LocalDateTime creation, LocalDateTime edition, String titre, Set<Image> images) {
		super(id, nom, creation, edition);
		this.titre = titre;
		this.images = images;
	}
	public Set<Image> getImages(){
		if (this.images == null) this.images = new HashSet<>();
		return this.images;
	}
	@PreRemove
	public void deleteImageLink() {
		//if (this.images.size()>0) this.images.clear();
		for(Image i : images) i.setGalerie(null);
	}
}
