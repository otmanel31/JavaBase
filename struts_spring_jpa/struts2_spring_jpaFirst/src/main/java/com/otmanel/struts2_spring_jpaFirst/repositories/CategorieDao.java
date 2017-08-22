package com.otmanel.struts2_spring_jpaFirst.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.otmanel.struts2_spring_jpaFirst.metier.Categorie;

public class CategorieDao implements ICategorieDao {
	private EntityManager em;
	
	public EntityManager getEm() {return em;}
	@PersistenceContext
	public void setEm(EntityManager em) {this.em = em;}
	
	@Override
	@Transactional //
	public List<Categorie> findAll(){
		return em.createQuery("from Categorie", Categorie.class).getResultList();
	}
	@Override
	@Transactional
	public Categorie findById(int id) {
		return em.find(Categorie.class, id);
	}
	@Override
	@Transactional
	public Categorie save(Categorie p) {
		if (p.getId() == 0 ) em.persist(p);
		else p = em.merge(p); //insert
		return p;
	}

	@Override
	@Transactional
	public void delete(int id) {
		Categorie p = em.find(Categorie.class, id);
		if (p != null) em.remove(p);
	}
}
