package com.otmanel.blogSecu.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otmanel.blogSecu.metier.Role;
@Service
public class RoleDao implements IRoleDao {
	private EntityManager em;
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Override
	@Transactional
	public List<Role> findAll(){
		return em.createQuery("from Role", Role.class).getResultList();
	}
	@Override
	@Transactional
	public Role findById(int id) {
		return em.find(Role.class, id);
	}
	
	@Override
	@Transactional
	public Role save(Role r ) {
		if( r.getId() == 0) em.persist(r);
		else r=em.merge(r);
		return r;
	}
}
