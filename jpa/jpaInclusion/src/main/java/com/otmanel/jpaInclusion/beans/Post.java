package com.otmanel.jpaInclusion.beans;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.otmanel.jpaInclusion.utils.HoraDateAble;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@EntityListeners(HoroDateur.class)
public class Post implements HoraDateAble{
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id 						private int id;
	@Column(length=100)			private String titre;
	@Column(length=500)			private String corps;
								private LocalDateTime creation;
								private LocalDateTime edition;
	
	
}
