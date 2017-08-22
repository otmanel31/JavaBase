package com.otmanel.struts2_spring_jpaFirst.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.otmanel.struts2_spring_jpaFirst.metier.Categorie;

public interface ICategorieDao {

	List<Categorie> findAll();

	Categorie findById(int id);

	Categorie save(Categorie p);

	void delete(int id);

}