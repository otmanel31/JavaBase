package com.otmanel.springmvcExoAngular4Facebook.repositorie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otmanel.springmvcExoAngular4Facebook.metier.Profil;

@Service
public class ProfilDao implements IProfilDao {
	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager m) {
		this.em = m;
	}
	
	@Override
	@Transactional
	public List<Profil> findAll(){
		return this.em.createQuery("from Profil", Profil.class).getResultList();
	}
	
	@Override
	@Transactional
	public Profil findById(int id) {
		return this.em.find(Profil.class, id);
	}
	
	@Override
	@Transactional
	public void delete(int pid) {
		Profil p = em.find(Profil.class, pid);
		if (p != null) em.remove(p);
	}
	
	@Override
	@Transactional
	public Profil save(Profil p) {
		if (p.getId() == 0) em.persist(p);
		else p = em.merge(p);
		return p;
	}
	@Override
	@Transactional
	public List<Profil> searchByName(String nom) {
		System.out.println("in search meth with args " + nom);
		TypedQuery<Profil> q = em.createQuery("select p from Profil as p where p.nom like:searchTerm", Profil.class);
		q.setParameter("searchTerm", "%"+nom+"%");
		return q.getResultList();
	}
	@Override
	@Transactional
	public List<Profil> searchByCity(String city) {
		System.out.println("in search meth with args city " + city);
		TypedQuery<Profil> q = em.createQuery("select p from Profil as p where p.ville like:searchTerm", Profil.class);
		q.setParameter("searchTerm", "%"+city+"%");
		return q.getResultList();
	}
	
}
