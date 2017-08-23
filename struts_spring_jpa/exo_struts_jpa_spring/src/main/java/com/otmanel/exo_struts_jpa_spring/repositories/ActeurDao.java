package com.otmanel.exo_struts_jpa_spring.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.otmanel.exo_struts_jpa_spring.metier.Acteur;

public class ActeurDao implements IActeurDao {
	private EntityManager em;
	
	
	@PersistenceContext
	public void setEm(EntityManager em) {this.em = em;}
	@Override
	@Transactional //
	public List<Acteur> findAll(){
		return findAll(false);
	}
	@Override
	@Transactional //
	public List<Acteur> findAll(boolean withFilm){
		// fetch == precharge cette association la
		if (withFilm)
			return em.createQuery("select distinct(f) from Acteur as f left join fetch f.films", Acteur.class).getResultList();
		else
			return em.createQuery("from Acteur", Acteur.class).getResultList();

	}
	@Override
	@Transactional //
	public List<Acteur> findAllNotInFilm(int fid){
		TypedQuery<Acteur> q =  em.createQuery("select a from Acteur as a "
				+ "where NOT EXISTS ("
				+ "select a2 from Acteur as a2 left join a2.films as f where a2.id = a.id AND f.id = :fid"
				+ ")", Acteur.class);
		
		q.setParameter("fid", fid);
		return q.getResultList();

	}
	@Override
	@Transactional
	public Acteur findById(int id) {
		return em.find(Acteur.class, id);
	}
}
