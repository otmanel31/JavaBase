package com.otmanel.jpaInclusion.jpa;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.otmanel.jpaInclusion.beans.Adresse;
import com.otmanel.jpaInclusion.beans.CleLivre;
import com.otmanel.jpaInclusion.beans.Geolocalisation;
import com.otmanel.jpaInclusion.beans.Livre;
import com.otmanel.jpaInclusion.beans.Post;
import com.otmanel.jpaInclusion.beans.Site;


public class JpaTest {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("testHibernate");
        Scanner input = new Scanner(System.in);

        input.nextLine();
        System.out.println("--------------------------------------");
		test1(emf);

        input.nextLine();
		System.out.println("--------------------------------------");
		test2(emf);

        input.nextLine();
		System.out.println("--------------------------------------");		

	    emf.close();
		System.out.println("done...");
	}




	public static void test1(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		Adresse a = new Adresse("rue basker ", "Londres", "12345", "Angleterre");
		Adresse b = new Adresse("21 jump street", "LA", "54321", "usa");
		
		Site s1 = new Site(0,"holmes home", a, new Geolocalisation(25.0, 13.0));
		Site s2 = new Site(0, "officer thomas", b, new Geolocalisation(0.0, 0.0));
		em.persist(s1);em.persist(s2);
		Livre l1 = new Livre(new CleLivre("1324676", LocalDate.of(2017, 11, 25)), "Jpa Forever"	, 250);
		em.persist(l1);
		//----------------------------------------------------
		tx.commit();
		em.close();
	}


	public static void test2(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		Site s1 = em.find(Site.class, 1);
		System.out.println(s1);
		System.out.println("----------------------------------------------");
		TypedQuery<Site> q1 = em.createQuery("select s from Site as s where s.adresse.pays=:pays", Site.class);
		q1.setParameter("pays", "usa");
		List<Site> sitess = q1.getResultList();
		for (Site s : sitess) System.out.println(s);
		System.out.println("----------------------------------------------");
		
		Livre l1 = em.find(Livre.class, new CleLivre("1324676", LocalDate.of(2017, 11, 25)));
		System.out.println(l1);
		System.out.println("Creation dun post----------------------------------------------");
		Post p = new Post(0, "unposr", "ksjbhvklsh", null, null);
		em.persist(p);
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
