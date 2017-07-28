package com.otmanel.videoTech.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.otmanel.videoTech.metiers.Film;

public class BddListener implements ServletContextListener {

    public BddListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("initialisation connection base");
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/videoTech",
					"root",
					"");
			/*
			// instanciation du dao des produits
			ProduitDAO produitDAO = new ProduitDAO(connection);
			// mettre celui-ci a disposition des servlets (et autre) de la webapp
			sce.getServletContext().setAttribute("produitDAO", produitDAO);
			*/
			
			GeneriqueDao<Film> produitDAO = new GeneriqueDao.GenericDaoBuilder<Film>(Film.class,
						connection,
						"id")
						.build();
					
					
			arg0.getServletContext().setAttribute("filmDAO", produitDAO);
			
			System.out.println("initialisation DAO terminée");
			
		} catch (ClassNotFoundException e) {e.printStackTrace();}
    	catch (SQLException e) {e.printStackTrace();}
    }
	
}
