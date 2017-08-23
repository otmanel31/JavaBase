package com.otmanel.exo_struts_jpa_spring.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.otmanel.exo_struts_jpa_spring.metier.Realisateur;

public class RealisateurDao implements IRealisateurDao {
	private EntityManager em;
	
	
	@PersistenceContext
	public void setEm(EntityManager em) {this.em = em;}
	@Override
	@Transactional //
	public List<Realisateur> findAll(){
		return findAll(false);
	}
	@Override
	@Transactional //
	public List<Realisateur> findAll(boolean withFilm){
		// fetch == precharge cette association la
		if (withFilm)
			return em.createQuery("select distinct(f) from Realisateur as f left join fetch f.films", Realisateur.class).getResultList();
		else
			return em.createQuery("from Realisateur", Realisateur.class).getResultList();

	}
	@Override
	@Transactional
	public Realisateur findById(int id) {
		return em.find(Realisateur.class, id);
	}
}
