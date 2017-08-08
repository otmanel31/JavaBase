package com.otmanel.jpa_exo2__cms.beans;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.otmanel.jpa_exo2__cms.utils.IDateable;

public class Dateur  {
	@PrePersist
	public void setCreation(IDateable entity) {
		LocalDateTime now = LocalDateTime.now();
		entity.setCreation(now);
		entity.setEdition(now);
	}
	@PreUpdate
	public void setEdition(IDateable entity) {
		LocalDateTime now = LocalDateTime.now();
		entity.setEdition(now);
	}
}
