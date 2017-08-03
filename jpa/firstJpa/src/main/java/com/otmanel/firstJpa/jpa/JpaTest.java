package com.otmanel.firstJpa.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.otmanel.firstJpa.beans.Message;

public class JpaTest {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("testHibernate");
			// =>  regarde les annotation ds les entité et decide en fonction de 
			// persistance.xml
        Scanner input = new Scanner(System.in);

        input.nextLine();
        System.out.println("--------------------------------------");
		test1(emf);

        input.nextLine();
		System.out.println("--------------------------------------");
		test2(emf);

        input.nextLine();
		System.out.println("--------------------------------------");		

	    emf.close(); // drop les tables
		System.out.println("done...");
	}




	public static void test1(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		//il gere des entité c a dire des objet metier a sauvegarder ou lire depuis la bdd
		// c via lenity manager que lon requete la bas eou demande la sauvegarde dun objet
		EntityManager em = emf.createEntityManager();
		// et une transaction
		// tt travail avec lunité de persistnace (bdd) seffectue normalement ds le contexte
		// dune transaction. celle ci ns est fournit par lentity manager
		// begin si dessous == debut de la transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		Scanner input = new Scanner(System.in);
		System.out.println("titre message ? ");
		String titre = input.nextLine();
		System.out.println("corps message ? ");
		String corps = input.nextLine();
		// on instancie un nouveau message 
		Message msg = new Message(0, titre, corps);
		// pour linstant msg contient un objet message
		// lentity manager ne connait pas cette instance il ny a aucnue liaison avec la base
		// on appele cela UN POJO (plain java object)
		em.persist(msg);
		// a partir de maintenant l'objet contenu dans msg est une entité "managé/suivie" par
				// l'entity manager
		//----------------------------------------------------
		// ici je demande a lentity manager de persister mon objet en basde
		// persist -> nouvelle objet a save en bdd -> insertion
		tx.commit();
		// ici commit de la transaction cad finir tous les travaux en suspend
		// que pourrait avoir lentity manager
		em.close();
		//fermetur d elentity manager, plus possible de requetter avec cet entity manager,
		// et il ne suit plus aucune aentité
	}


	public static void test2(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		Scanner input = new Scanner(System.in);
		System.out.println("identifiant message? ");
		int id = Integer.parseInt(input.nextLine());
		// find permet de trouver une entité depuis la base en indiquant la valeur dune pk
		// 2 param (entité et id)
		Message msg = em.find(Message.class, id);
		if (msg != null) {
		    System.out.println(msg.getTitre());
		    System.out.println(msg.getCorps());
        }
        else {
		    System.out.println("message inconnu...");            
        }
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
