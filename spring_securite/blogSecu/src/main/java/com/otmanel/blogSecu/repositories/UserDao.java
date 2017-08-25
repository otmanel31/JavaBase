package com.otmanel.blogSecu.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otmanel.blogSecu.metier.User;

@Service
public class UserDao implements IUserDDao {
	private EntityManager em;
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	@Override
	@Transactional
	public List<User> findAll(){
		return em.createQuery("from User", User.class).getResultList();
	}
	@Override
	@Transactional
	public User findById(int id) {
		return em.find(User.class, id);
	}
	@Override
	@Transactional
	public User findByUserName(String username) {
		TypedQuery<User> q = em.createQuery("select u from User as u where u.username=:name", User.class);
		q.setParameter("name", username);
		//try { on laisse la charge a lappelant pour gerer cette exception
			return q.getSingleResult();
		//}catch(NoResultException e) {e.printStackTrace();}
		
	}
	@Override
	@Transactional
	public User save(User u ) {
		if(u.getId() != 0) {
			User old = em.find(User.class, u.getId());
			if (old != null) {
				u.setRoles(old.getRoles());
			}
			u = em.merge(u);
		}
		else 
			em.persist(u);
		return u;
	}
}
