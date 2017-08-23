package com.otmanel.exo_struts_jpa_spring.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.otmanel.exo_struts_jpa_spring.metier.Acteur;
import com.otmanel.exo_struts_jpa_spring.metier.Film;


public class FilmDao implements IFilmDao {
	private EntityManager em;
	
	
	@PersistenceContext
	public void setEm(EntityManager em) {this.em = em;}
	@Override
	@Transactional //
	public List<Film> findAll(){
		return findAll(false);
	}
	@Override
	@Transactional //
	public List<Film> findAll(boolean withActeurs){
		// fetch == precharge cette association la
		if (withActeurs)
			return em.createQuery("select distinct(f) from Film as f left join fetch f.acteurs", Film.class).getResultList();
		else
			return em.createQuery("from Film", Film.class).getResultList();

	}
	@Override
	@Transactional
	public Film findById(int id) {
		return em.find(Film.class, id);
	}
	@Override
	@Transactional
	public Film save(Film p) {
		if (p.getId() == 0 ) em.persist(p);
		else {
			// je reassocie mon objet film en provenance du formalaire avc son casting( ses acteurs ;) )
			/*Film filmOld = em.find(Film.class, p.getId());
			p.setActeurs(filmOld.getActeurs()); commenté pour voir la variante dans filmAction*/
			p = em.merge(p); //insert
		}
		return p;
	}
	@Override
	@Transactional
	public void delete(int id) {
		Film p = em.find(Film.class, id);
		if (p != null) em.remove(p);
	}
	@Override
	@Transactional
	public void removeActeurFromFilm(int fid, int aid){
		Film f = em.find(Film.class, fid);
		Acteur a = em.find(Acteur.class, aid);
		if (f == null || a == null) return;
		f.getActeurs().remove(a);
		
	}
	@Override
	@Transactional
	public void addActeurToFilm(int filmId, int acteurId) {
		Film f = em.find(Film.class, filmId);
		Acteur a = em.find(Acteur.class, acteurId);
		if (f == null || a == null) return;
		f.getActeurs().add(a);
	}
}
