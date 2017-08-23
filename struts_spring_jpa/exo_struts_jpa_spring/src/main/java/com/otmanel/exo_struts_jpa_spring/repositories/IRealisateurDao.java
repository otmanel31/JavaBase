package com.otmanel.exo_struts_jpa_spring.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.otmanel.exo_struts_jpa_spring.metier.Film;
import com.otmanel.exo_struts_jpa_spring.metier.Realisateur;

public interface IRealisateurDao {

	List<Realisateur> findAll();

	List<Realisateur> findAll(boolean withFilm);

	Realisateur findById(int id);

}