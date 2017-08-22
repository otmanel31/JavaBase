package com.otmanel.struts2_spring_jpaFirst.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.otmanel.struts2_spring_jpaFirst.metier.Categorie;
import com.otmanel.struts2_spring_jpaFirst.metier.Produit;

public class CatDao implements ICatDao {
	private EntityManager em;
	
	public EntityManager getEm() {return em;}
	//indique a spring de minjecter un entity manager pret a lemploi quand jen ai besoin
	@PersistenceContext
	public void setEm(EntityManager em) {this.em = em;}

	@Override
	@Transactional
	public void removeCategorieFromProduit(int idProd, int idCat) {
		Categorie c = em.find(Categorie.class, idCat);
		Produit p = em.find(Produit.class, idProd);
		
		if (c == null || p==null) return;
		//retirer le produit de la categorie sil y est
		/**
		 * ATTENTION BUG dans javassist si lambda et stream utiliser
		 * il narive pas a geneerer laop
		 */
		//c.getProduits().removeIf(pr->pr.getId() == c.getId());
		c.getProduits().remove(p);
	}
	@Override
	@Transactional
	public void addCategorieToProduit(int idProd, int idCat) {
		Categorie c = em.find(Categorie.class, idCat);
		Produit p = em.find(Produit.class, idProd);
		if (c == null || p==null) return;
		//retirer le produit de la categorie sil y est
		if (!c.getProduits().contains(p)) c.getProduits().add(p);
	}
}	
