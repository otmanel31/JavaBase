package com.otmanel.basicMapping.jpa;

import java.util.*;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.otmanel.basicMapping.beans.*;

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

        /*input.nextLine();
		System.out.println("--------------------------------------");
		test2(emf);
		 */
		/*input.nextLine();
        System.out.println("--------------------------------------");
		test4(emf);
        input.nextLine();
		System.out.println("--------------------------------------");	*/

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
		em.persist(new Produit(0, "steak de lama", 10.00, 1.25, 10, new Date()));
		em.persist(new Produit(0, "tofu tofu", 15.00, 1.2, 50, new Date()));
		em.persist(new Produit(0, "ramen", 2.50, 1.00, 10, new Date()));
		em.persist(new Produit(0, "quinoa des andes", 70.00, 1.25, 50, new Date()));
		
		Produit p1 = em.find(Produit.class, 1);
		System.out.println(p1.toString());
		
		TypedQuery<Produit> q1 = em.createQuery("FROM Produit", Produit.class);
		List<Produit> produits = q1.getResultList();
		for(Produit p : produits) System.out.println(p);
		// :pmin == parametre nomme
		TypedQuery<Produit> q2 = em.createQuery("SELECT p FROM Produit as p where p.prix> :pmin", Produit.class);
		
		q2.setParameter("pmin", 20.00);
		produits = q2.getResultList();
		for(Produit p : produits) System.out.println(p);
		
		Query q3 = em.createQuery("SELECT p.nom, p.prix from Produit as p where p.stock > :stockmin");
		q3.setParameter("stockmin", 25);
		
		List<Object[]> data = q3.getResultList();
		for(Object[] l : data) System.out.println(Arrays.toString(l));
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
	public static void test3(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		Produit p1 = em.find(Produit.class, 1);
		p1.setPrix(p1.getPrix()+15); // augmentation du prix de 15e
		System.out.println("prix apres update " + p1);
		
		
		TypedQuery<Produit> q1 = em.createQuery("FROM Produit", Produit.class);
		List<Produit> produits = q1.getResultList();
		for(Produit p : produits) System.out.println(p);
		//----------------------------------------------------
		tx.commit();
		em.close();
	}
	public static void test4(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		Produit p1 = em.find(Produit.class, 1);
		Produit p2 = em.find(Produit.class, 2);
		
		//effacement dun produit avec lentité et nn id 
		em.remove(p2);
		p1.setStock(42);
		
		//----------------------------------------------------
		tx.commit();
		em.close();
		// une fois lentity manager fermé, les objet sont ds un etats détachés
		// c a dire kelle ne sont plus suivi et donc que le smlanipulation dessus non plyus deffets sur la base
		// un e entité détaché lest definitivement
		// on ne peux pas réatacher p1 a u autre entity manager
		p1.setStock(54);
		System.out.println("p1 " + p1);
		Scanner input = new Scanner(System.in);
		input.nextLine();
		System.out.println("----------------------------------------");
		
		EntityManager em2 = emf.createEntityManager();
		EntityTransaction tx2 = em2.getTransaction();
		tx2.begin();
		Produit newp1 = em2.merge(p1);
		System.out.println("newp1 == p1 ? " + (newp1 == p1));
		tx2.commit();
		em2.close();
	}

}
