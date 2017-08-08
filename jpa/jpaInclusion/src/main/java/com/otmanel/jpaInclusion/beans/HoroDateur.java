package com.otmanel.jpaInclusion.beans;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.otmanel.jpaInclusion.utils.HoraDateAble;

public class HoroDateur  {
	@PrePersist
	public void setCreation(HoraDateAble entity) {
		System.out.println("appel de set creation sur " + entity);
		LocalDateTime ldt = LocalDateTime.now();
		entity.setCreation(ldt);
		entity.setEdition(ldt);
	}
	
	@PreUpdate
	public void setMaj(HoraDateAble entity) {
		System.out.println("appel de set maj sur " + entity);

		LocalDateTime ldt = LocalDateTime.now();
		entity.setEdition(ldt);
	}
}
