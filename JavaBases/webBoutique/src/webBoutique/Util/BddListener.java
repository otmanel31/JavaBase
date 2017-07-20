package webBoutique.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import webBoutique.Dao.ProduitDao;

@WebListener
public class BddListener implements ServletContextListener {

    public BddListener() {

    }
    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("initialisation connexion base");
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/boutique", 
					"root", "");
			// instanciation du dao des produit qui est fournit au servlet et pas de la 
			// connectuiono imeditament elle vont jsute manipuler des objets dao et n'a pas a savoir comment sa
			// sze passe et le details
			ProduitDao produitDAO = new ProduitDao(connection);
			// mettre celui ci a la disposition des servlet et autres de la webapps
			arg0.getServletContext().setAttribute("produitDao", produitDAO);
			System.out.println("initialisation DAO terminé");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void contextDestroyed(ServletContextEvent arg0)  { 

    }


	
}
