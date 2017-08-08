package com.otmanel.exo1_jpa.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
			Etudiant f = null;
			if(i<50) {
				 f = new Etudiant(0, "stupidMen"+i, "stupid"+i, "stupidstudent"+i+"@std.edu");
				em.persist(f);
			}
			else if (i>50 && i <=98) {
				 f = new Etudiant(0, "VerygoodMen"+i, "VerygoodMen"+i, "VerygoodMen"+i+"@std.edu");
				em.persist(f);
			}
			else {
				 f = new Etudiant(0, "ZEBEST"+i, "ZEBEST"+i, "ZEBEST"+i+"@std.edu");
				em.persist(f);
			}
			etudiants[i] = f;
			
		}
		String[] libelleMatiere = {"JAVA", "MaTH","anglais", "histoire", "linux", "Algo", "reseaux", "AAlgo", "reseaux"};
		String[] libelleCours = {"intro java", "fonction affine", "verbes modaux", "culture 2.0", "script shell", "python"
				, "ldap", "strutre de donnee", "tcp/ip"};
		
		Random r = new Random();
		for(int j = 0; j<10; j++) { //  corriger+
			int matiereId = r.nextInt(matieres.length);
			String lib = libelleMatiere[matiereId] + " - " + libelleCours[r.nextInt(libelleCours.length)];
			
			Cours c = new Cours(0, lib	, new Date(118, r.nextInt(5), 5), new Date(118, r.nextInt(5) + 5, 5),r.nextInt(12));
			int count = 0;
			for (Etudiant e: etudiants) {
				//if (c.getCapaciteMax() == count) break;
				if (r.nextDouble()>0.75) {
					e.getListCours().add(c);
				}
				//int idToAdd = r.nextInt(etudiants.length);
				
				//c.getEtudiants().add(etudiants[idToAdd]);
				//count ++ ;
			}
			int idToAd = r.nextInt(profs.length);
			c.setProf(profs[idToAd]);
			Matiere m = em.find(Matiere.class, matiereId);

			c.setMatiere(m);
			em.persist(c);
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
		
		System.out.println("REQUETE 1 ");
		Query query1 = em.createQuery("select e, c from Etudiant as e join e.listCours as c where c.dateDebut>:dateDebut");
		query1.setParameter("dateDebut", new Date(118, 3,1));
		List<Object[]> cours = query1.getResultList();
		for (Object[] e : cours) {
			System.out.println(e[0] + "**** " + e[1]);
			//for (Cours c :e.getListCours()) System.out.println("date debut " + c.getDateDebut() );
		}
		System.out.println("REQUETE 2 ");
		Query qCours = em.createQuery("Select c, COUNT(e.id) from Cours as c join c.etudiants as e group by c.id ");
		cours = qCours.getResultList();
		for (Object[] c : cours) System.out.println(c[0] + " **** " + c[1]);
		System.out.println("REQUETE 3 ");
		Query qCours2 = em.createQuery("Select c, COUNT(e.id)*100/c.capaciteMax from Cours as c join c.etudiants as e group by c.id ");
		cours = qCours2.getResultList();
		for (Object[] c : cours) System.out.println(c[0] + " **** " + c[1]);
		
		System.out.println("REQUETE 4 ");
		Query qCours4 = em.createQuery("Select m.libelle, COUNT(distinct e.id) "
				+ "from Matiere as m join m.listCours as c "
				+ "join c.etudiants as e group by m");
		cours = qCours2.getResultList();
		for (Object[] c : cours) System.out.println(Arrays.toString(c));
		
		System.out.println("REQUETE 5 ");
		TypedQuery<Etudiant> qCours5 = em.createQuery("select distinct(e) from Etudiant as e "
				+ "join e.listCours as c where c.prof.id = :pid",
				Etudiant.class);
		qCours5.setParameter("pid", 1);
		List<Etudiant> estudiantes = qCours5.getResultList();
		for (Etudiant e : estudiantes) System.out.println(e);
		System.out.println("REQUETE  6 ");
		TypedQuery<Etudiant> qCours6 = em.createQuery("select e from Etudiant as e "
				+ "where not exists ("
				+ "select e2 from Etudiant as e2 join e2.listCours as c where e2.id = e.id AND c.prof.id = :pid"
				+ ")",
				Etudiant.class);
		qCours6.setParameter("pid", 1);
		estudiantes = qCours6.getResultList();
		for (Etudiant e : estudiantes) System.out.println(e);
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
