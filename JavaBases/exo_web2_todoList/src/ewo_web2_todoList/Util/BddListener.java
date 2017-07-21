package ewo_web2_todoList.Util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ewo_web2_todoList.Dao.TodoDAO;

@WebListener
public class BddListener implements ServletContextListener {

    public BddListener() {
    	
    }


    public void contextDestroyed(ServletContextEvent arg0)  { 
    	
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("initialisation connexion bdd");
    	try {
    		// recuperation du conneceteur my sql
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tptodo", "root","");
    		// instanciation du dao
    		TodoDAO todoDAO= new TodoDAO(connexion);
    		arg0.getServletContext().setAttribute("todoDAO", todoDAO);
    		System.out.println("");
    	}catch(ClassNotFoundException | SQLException e) {e.printStackTrace();}
    }
	
}
