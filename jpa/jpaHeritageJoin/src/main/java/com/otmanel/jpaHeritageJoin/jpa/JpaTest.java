package com.otmanel.jpaHeritageJoin.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.otmanel.jpaHeritageJoin.beans.*;

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
		for (int i =0; i<=10; i++) {
			em.persist(new Personne(0, "bob" +i, "joe"+i));
		}
		Random rd = new Random();
		for (int i = 0; i<=10; i++) {
			em.persist(new Client(0, "anrold"+i, "willy"+i, "arnold"+i +"@willy.com", 2000.0));
		}
		for (int i = 0; i<=10; i++) {
			em.persist(new Employe(0, "sylvester", "commandant", "bourrineur", rd.nextDouble()*4000.0));
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
		
		List<Client> clients = em.createQuery("select c from Client as c", Client.class).getResultList();
		for( Client c : clients) System.out.println(c);
		System.out.println("------------------------------ ");
		List<Personne> personnes = em.createQuery("select p from Personne as p", Personne.class)
					.getResultList();
		for(Personne p : personnes) System.out.println(p);
		System.out.println("------------------------------ ");
		Employe emp = em.find(Employe.class, 25);
		em.remove(emp);
		Client cli = em.find(Client.class, 15);
		cli.setSoldeCompte(cli.getSoldeCompte() + 10000);
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
