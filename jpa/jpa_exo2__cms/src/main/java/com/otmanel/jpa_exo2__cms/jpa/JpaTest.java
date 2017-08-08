package com.otmanel.jpa_exo2__cms.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.otmanel.jpa_exo2__cms.beans.DocumentPdf;
import com.otmanel.jpa_exo2__cms.beans.Tag;

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
		System.out.println("INSERTION DONNEES - TAGS");
		String[] libelles = {"Un pour les gouverner ts", "maitre corbeaux", "Java pour les noobs", "Le lengende urbaine du bug"
					, "Big data"};
		
		Tag[] tags = new Tag[5];
		for(int i= 0; i<=4;i++) { 
			tags[i] = new Tag(0, libelles[i]);
			em.persist(tags[i]);
		}
		System.out.println("INSERTION DONNEES - DOCUMENTS PDF");
		DocumentPdf[] docs= new DocumentPdf[31];
		for (int i=0; i< docs.length; i++) {
			if (i <10)
				docs[i] = new DocumentPdf(0, "un nom " + i, null, null, "un titre "+i, "badman"+i);
			else if (i >=10 && i <25)
				docs[i] = new DocumentPdf(0, "un autre " + i, null, null, "Ze Title "+i, "shakes"+i);
			else docs[i] = new DocumentPdf(0, "GROS TITRE " + i, null, null, "Lords of the rings "+i, "Tolkien"+i);
			em.persist(docs[i]);
		}
		
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
		
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
