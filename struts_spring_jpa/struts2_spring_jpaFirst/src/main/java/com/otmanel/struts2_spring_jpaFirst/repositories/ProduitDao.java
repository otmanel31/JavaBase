package com.otmanel.struts2_spring_jpaFirst.repositories;

import java.awt.Point;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.transaction.annotation.Transactional;

import com.otmanel.struts2_spring_jpaFirst.metier.Produit;

// DAO DE TYPE JPA
// pour requeter la base on a besoin tt dabord de lentity manager
// pui son demande a spring de linjecter, le bean declarer ds lapp context => entitymanager factory,
// lit les classe java et injecte des entity manager en fonctiion de lendroit ou il trouve les annotations
// desirés -> ci dessou PersistenceContext
public class ProduitDao implements IProduitDao {

	private EntityManager em;
	
	public EntityManager getEm() {return em;}
	//indique a spring de minjecter un entity manager pret a lemploi quand jen ai besoin
	@PersistenceContext
	public void setEm(EntityManager em) {this.em = em;}

	//indique a spring que cette methode va requeter la bdd via lunité de persistance
	// ( destiné au transaction manager)
	//Ainsi spring va se charge dinitialiser une transaction avant le demarage de la fonction et
	// de la commiter juste a la fin de son execution (via de l'aop)
	@Override
	@Transactional //
	public List<Produit> findAll(){
		return findAll(false);
	}
	@Override
	@Transactional //
	public List<Produit> findAll(boolean withCategories){
		// fetch == precharge cette association la
		if (withCategories)
			return em.createQuery("select distinct(p) from Produit as p left join fetch p.categories", Produit.class).getResultList();
		else
			return em.createQuery("from Produit", Produit.class).getResultList();

	}
	@Override
	@Transactional
	public Produit findById(int id) {
		return em.find(Produit.class, id);
	}
	@Override
	@Transactional
	public Produit save(Produit p) {
		if (p.getId() == 0 ) em.persist(p);
		else p = em.merge(p); //insert
		return p;
	}
	@Override
	@Transactional
	public void delete(int id) {
		Produit p = em.find(Produit.class, id);
		if (p != null) em.remove(p);
	}
}
