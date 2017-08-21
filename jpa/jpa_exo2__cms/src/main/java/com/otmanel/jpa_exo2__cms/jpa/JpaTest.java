package com.otmanel.jpa_exo2__cms.jpa;

import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.otmanel.jpa_exo2__cms.beans.Content;
import com.otmanel.jpa_exo2__cms.beans.DocumentPdf;
import com.otmanel.jpa_exo2__cms.beans.Galerie;
import com.otmanel.jpa_exo2__cms.beans.Image;
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
		DocumentPdf[] docs= new DocumentPdf[50];
		for (int i=0; i< docs.length; i++) {
			if (i <10)
				docs[i] = new DocumentPdf(0, "un nom " + i, null, null, "un titre "+i, "badman"+i);
			else if (i >=10 && i <25)
				docs[i] = new DocumentPdf(0, "un autre " + i, null, null, "Ze Title "+i, "shakes"+i);
			else docs[i] = new DocumentPdf(0, "GROS TITRE " + i, null, null, "Lords of the rings "+i, "Tolkien"+i);
			em.persist(docs[i]);
		}
		System.out.println("INSERTION DONNEES - Gallerie ");
		Galerie[] galeries = new Galerie[3];
		for(int i = 0; i <galeries.length; i++) {
			galeries[i] = new Galerie(0, "galerie"+i, null, null, "titreGalerie"+i, null);
			em.persist(galeries[i]);
		}
		
		String[] types = {"jpg", "png", "gif"};
		Image[] images = new Image[47];
		Random rd = new Random();
		for(int i = 0; i < images.length; i++) {
			images[i] = new Image(0, "image"+i, null, null, "imageName"+i, types[rd.nextInt(types.length-1)]);
			Galerie g = galeries[rd.nextInt(galeries.length)];
			images[i].setGalerie(g);
			for (int j=0; j<= tags.length; j++) {
				if (rd.nextBoolean()) images[i].getTags().add(tags[rd.nextInt(tags.length-1)]);

			}
			
			em.persist(images[i]);
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
		System.out.println("---------------------------------- --- MES REQUETES --------------------------------------------- ");
		System.out.println("1- ---------------- req one -----------------");
		TypedQuery<Image> q1 = em.createQuery("select i from Image as i join i.tags as t where t.libelle=:libParam", Image.class);
		q1.setParameter("libParam", "Un pour les gouverner ts");
		List<Image> images = q1.getResultList();
		for( Image i: images) System.out.println("mon image " + i /*+" \n" + " ------------> tag : "*/);
		
		System.out.println("2- ---------------- req two -----------------");
		Query q2 = em.createQuery("select g, COUNT(i.id) from Galerie as g join g.images as i"
				+ " group by g.id");
		List<Object[]> galeries = q2.getResultList();
		for (Object[] g : galeries) System.out.println(Arrays.toString(g));
		
		System.out.println("3- ---------------- req three -----------------");
		TypedQuery<Content> q3 = em.createQuery("select c from Content as c join c.tags as t1 join c.tags as t2 "
				+  "where t1.libelle=:param1 AND t2.libelle=:param2 ", Content.class);
		q3.setParameter("param1", "maitre corbeaux");
		q3.setParameter("param2", "Java pour les noobs");
		List<Content> contents = q3.getResultList();
		for (Content c : contents) System.out.println("content twice params " + c);
		
		System.out.println("4- ---------------- req four -----------------");
		Query q4 = em.createQuery("select t, count(c.id) from Tag as t join t.contents as c group by t.id");
		List<Object[]> tags = q4.getResultList();
		for (Object[] t : tags) System.out.println(Arrays.toString(t));
		
		System.out.println("5- ---------------- req five -----------------");
		TypedQuery<Content> q5 = em.createQuery("select c from Content as c where c.edition> :date", Content.class);
		q5.setParameter("date", LocalDateTime.now().minusMinutes(1));
		List<Content> contentss  = q5.getResultList();
		for (Content c : contentss) System.out.println(c);
		
		System.out.println("6- ---------------- req six -----------------");
		TypedQuery<Image> q6 = em.createQuery("select i from Image as i join i.tags as t where t.id = :tid AND "
				+ "NOT EXISTS("
				+ "select i2 from Image as i2 join i2.tags as t2 where t2.id = :tid2 AND i2.id = i.id"
				+ ")", Image.class);
		q6.setParameter("tid", 1);
		q6.setParameter("tid2", 2);
		List<Image> imagines = q6.getResultList();
		for (Image i: imagines) System.out.println(i);
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
