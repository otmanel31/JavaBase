package com.otmanel.exo_struts_jpa_spring.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.otmanel.exo_struts_jpa_spring.metier.Acteur;

public interface IActeurDao {

	List<Acteur> findAll();

	List<Acteur> findAll(boolean withFilm);

	Acteur findById(int id);

	List<Acteur> findAllNotInFilm(int fid);

}