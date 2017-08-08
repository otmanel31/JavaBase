package com.otmanel.simpleAssociation.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.dialect.function.AvgWithArgumentCastFunction;

import com.otmanel.simpleAssociation.beans.*;

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
		
		/*input.nextLine();
		System.out.println("--------------------------------------");
		test3(emf);*/
		
		input.nextLine();
		System.out.println("--------------------------------------");
		test4(emf);
		
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
		em.persist(new Genre(0, "science fiction"));
		em.persist(new Genre(0, "comedie"));
		em.persist(new Genre(0, "fantastique"));
		em.persist(new Genre(0, "western"));

		Acteur[] acteurs = new Acteur[6];
		acteurs[0] = new Acteur(0, "wilis", "bruce");
		acteurs[1] = new Acteur(0, "Stalonne", "Sylverster");
		acteurs[2] = new Acteur(0, "chuck", "norris");
		acteurs[3] = new Acteur(0, "Mickey", "Rourke");
		acteurs[4] = new Acteur(0, "Lee", "Jet");
		acteurs[5] = new Acteur(0, "Dujardin", "Jean");
		
		for (Acteur a : acteurs) em.persist(a);
		String[] sujets = {"attaque", "donuts", "retour", "cow boys", "alien", "zombie" };
		String[] liaisons = {"du", "des", "contre", "de" };
		String[] action = {"attaque", "rencontre", "combat", "etats d'ame"};
		
		Random rd = new Random();
		int ii = 10;
		for (int i = 0; i<10; i++) {
			String titre = action[rd.nextInt(action.length)] + 
					" " + liaisons[rd.nextInt(liaisons.length)] + 
					" " + sujets[rd.nextInt(sujets.length)] ;
			Film f  = new Film(0, titre, new Date(), 120 + i); ii+=50;
			// tirer au sort un genre
			f.setGenre(em.find(Genre.class, rd.nextInt(5)+1));
			// chaque acteur a une chance sur 2 de participer a ce film
			for (Acteur a: acteurs) {
				if (rd.nextBoolean()) f.getActeurs().add(a);
			}
			// save film
			em.persist(f);
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
		
		Film f1 = em.find(Film.class, 1);
		System.out.println("titre fim => " + f1.getTitre());
		System.out.println("genre film => " + f1.getGenre().getLibelle());
		for (Acteur a : f1.getActeurs()) {
			System.out.println("acteur => " + a.toString());
		}
		Film f2 = em.find(Film.class, 2);
		
		//----------------------------------------------------
		tx.commit();
		em.close();
		// erreur lazy si dessous
		/*System.out.println("titre fim => " + f2.getTitre());
		//System.out.println("genre film => " + f2.getGenre().getLibelle());
		for (Acteur a : f2.getActeurs()) {
			System.out.println("acteur => " + a.toString());
		}*/
	}
	public static void test3(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		/*Genre g1 = em.find(Genre.class, 1);
		em.remove(g1);*/ // erreur ici impossible de suppriemer un genre
		 // idem si on fait la mm chose sur des acteirs
		/*Film f1 = em.find(Film.class, 1);
		em.remove(f1);  // ici sa marche*/
		/*Genre g1 = em.find(Genre.class, 1);
		for (Film f : g1.getFilms()) {
			System.out.println(f);
		}*/
		/*
		Film f1 =  new Film(0, "valerian a la mzer", new Date(), 120);
		Genre g1 = new Genre(0, "navet");
		f1.setGenre(g1);
		//em.persist(g1);
		em.persist(f1); // ne marche si la ligne du dessus est en cascade= PERSIST ds film ->genre
		*/
		Film f1 =  new Film(0, "valerian a la mzer", new Date(), 120);
		Acteur a1 = new Acteur(0, "moi", "moi");
		em.persist(a1);
		f1.getActeurs().add(a1);
		em.persist(f1);
		//a1.getFilms().add(f1); tjr passer par le coté maitre
		// ceci naura aucun effetv en base 
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
		// liste de films
		TypedQuery<Film> queryFilms = em.createQuery(
				"select f from Film as f where f.genre.id=:gid",
				Film.class);
		queryFilms.setParameter("gid", 1);
		List<Film> films = queryFilms.getResultList();
		for (Film f : films) System.out.println(f); // tt les films du genre n°1
		System.out.println(" ----------------------------------------- ");
		
		TypedQuery<Film> q2 = em.createQuery("select f from Film as f where f.genre.libelle=:libelle",
				Film.class);
		q2.setParameter("libelle", "comedie");
		films  = q2.getResultList();
		for (Film f : films) System.out.println("test" + f);
		System.out.println(" ----------------------------------------- ");
		
		TypedQuery<Film> q3 = em.createQuery("select f from Film as f join f.acteurs as a where a.nom=:zacteur_nom",
				Film.class);
		q3.setParameter("zacteur_nom", "wilis");
		films  = q3.getResultList();
		for (Film f : films) System.out.println("acteur" + f); // tt les films du genre n°1
		System.out.println(" ----------------------------------------- ");
		
		Query queryCalcul1 = em.createQuery("select a.id, a.nom, count(f.id) from Acteur as a "
				+ "join a.films as f group by a.id ");
		List<Object[]> stats = queryCalcul1.getResultList();
		for(Object[] row : stats) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println(" ----------------------------------------- ");
		Query queryCalcul2 = em.createQuery("select a.id, a.nom, avg(f.dureeMin) from Acteur as a "
				+ "join a.films as f group by a.id ");
		stats = queryCalcul2.getResultList();
		for(Object[] row : stats) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println(" ----------------------------------------- ");
		TypedQuery<Acteur> queryActeur = em.createQuery("select a from Acteur as a where NOT EXISTS("
				+ "select f from Film as f join f.acteurs as a2 where f.id=:dif AND a2.id= a.id"
				+ ")"
				, Acteur.class);
		queryActeur.setParameter("dif", 1);
		List<Acteur> auteurss = queryActeur.getResultList();
		for(Acteur a : auteurss) {
			System.out.println("acteurs nom present ds un film => " + a);
		}
		System.out.println(" ----------------------------------------- ");
		// equivalent a la synbtaxe join avc le mont clé in
		TypedQuery<Film> q5 = em.createQuery("select DISTINCT(f) from Film as f,"
				+ " IN(f.acteurs) a where a.nom=:zacteur_nom",
				Film.class);
		q3.setParameter("zacteur_nom", "wilis");
		films  = q3.getResultList();
		for (Film f : films) System.out.println("acteur q5 " + f); // tt les films du genre n°1
		System.out.println(" ----------------------------------------- ");
		//----------------------------------------------------
		tx.commit();
		em.close();
	}
}
