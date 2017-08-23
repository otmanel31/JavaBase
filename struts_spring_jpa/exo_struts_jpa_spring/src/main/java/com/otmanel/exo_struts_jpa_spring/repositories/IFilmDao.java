package com.otmanel.exo_struts_jpa_spring.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.otmanel.exo_struts_jpa_spring.metier.Film;

public interface IFilmDao {

	List<Film> findAll();

	List<Film> findAll(boolean withCategories);

	Film findById(int id);

	Film save(Film p);

	void delete(int id);

}