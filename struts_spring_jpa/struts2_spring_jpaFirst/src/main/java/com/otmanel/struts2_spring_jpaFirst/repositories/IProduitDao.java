package com.otmanel.struts2_spring_jpaFirst.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.otmanel.struts2_spring_jpaFirst.metier.Produit;

public interface IProduitDao {

	//indique a spring que cette methode va requeter la bdd via lunité de persistance
	// ( destiné au transaction manager)
	//Ainsi spring va se charge dinitialiser une transaction avant le demarage de la fonction et
	// de la commiter juste a la fin de son execution (via de l'aop)
	List<Produit> findAll();
	List<Produit> findAll(boolean withCategorie);
	Produit findById(int id);
	Produit save(Produit p);
	void delete(int id);
}