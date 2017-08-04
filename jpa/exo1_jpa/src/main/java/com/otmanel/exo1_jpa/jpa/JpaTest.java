package com.otmanel.exo1_jpa.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.otmanel.exo1_jpa.beans.Cours;
import com.otmanel.exo1_jpa.beans.Etudiant;
import com.otmanel.exo1_jpa.beans.Matiere;
import com.otmanel.exo1_jpa.beans.Professeur;


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
		System.out.println(" *************** ALIMENTATION BDD *******************");
		/*Cours[] cours = new Cours[6];
		cours[0] = new Cours(0, "fonction affine", new Date(), new Date(118, 5, 26), 15);
		cours[1] = new Cours(0, "algo intro", new Date(), new Date(118, 4, 26), 8);
		cours[2] = new Cours(0, "anglais - presnetation", new Date(), new Date(118, 5, 12), 6);
		cours[3] = new Cours(0, "reseaux - tcp/ip", new Date(), new Date(118, 5, 26), 8);
		cours[4] = new Cours(0, "developpement / intro java", new Date(), new Date(118, 7, 26), 8);
		cours[5] = new Cours(0, "culture 2.0: histoire du web", new Date(), new Date(118, 4, 26), 10);
		for (Cours c : cours) em.persist(c);*/
		Matiere[] matieres = new Matiere[6];
		matieres[0] = new Matiere(0, "math");
		matieres[1] = new Matiere(0, "algo");
		matieres[2] = new Matiere(0, "math");
		matieres[3] = new Matiere(0, "anglais");
		matieres[4] = new Matiere(0, "reseaux");
		matieres[5] = new Matiere(0, "developpement");
		for (Matiere m : matieres) em.persist(m);
		Professeur[] profs = new Professeur[6];
		profs[0] = new Professeur(0, "courtalon", "vincent", 100000);
		profs[1] = new Professeur(0, "Banner", "hulk", 500);
		profs[2] = new Professeur(0, "hawking", "stephen", 50000);
		profs[3] = new Professeur(0, "averoes", "idontknow", 0.50);
		profs[4] = new Professeur(0, "openheimer", "mahatan", 1000000);
		profs[5] = new Professeur(0, "Otman", "moi", 15000);
		for (Professeur p : profs) em.persist(p);
		
		Etudiant[] etudiants = new Etudiant[100];
		for (int i=0; i< 100; i++) {
			if(i<=25) {
				Etudiant f = new Etudiant(0, "stupidMen"+i, "stupid"+i, "stupidstudent"+i+"@std.edu");
				em.persist(f);
			}else if(i>25 && i<=50) {
				Etudiant f = new Etudiant(0, "BofstupidMen"+i, "stupid"+i, "bof"+i+"@std.bof");
				em.persist(f);
			}
			else if (i>50 && i <=75) {
				Etudiant f = new Etudiant(0, "goodMen"+i, "goodMen"+i, "goodMen"+i+"@std.edu");
				em.persist(f);
			}
			else if (i>75 && i <=98) {
				Etudiant f = new Etudiant(0, "VerygoodMen"+i, "VerygoodMen"+i, "VerygoodMen"+i+"@std.edu");
				em.persist(f);
			}
			else {
				Etudiant f = new Etudiant(0, "ZEBEST"+i, "ZEBEST"+i, "ZEBEST"+i+"@std.edu");
				em.persist(f);
			}
			
		}
		String[] libelleCours = {"intro java", "fonction affine", "verbes modaux", "culture 2.0", "script shell", "python"
				, "ldap", "strutre de donnee", "tcp/ip"};
		
		Random r = new Random();
		for(int j = 0; j<10; j++) { //  corriger
			Cours c = new Cours(0, "intro-java"	, new Date(), new Date(118, 5, 26), 8);
			
		}
		//--------- -------------------------------------------
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
