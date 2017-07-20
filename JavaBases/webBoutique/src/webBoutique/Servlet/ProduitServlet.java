package webBoutique.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.scene.control.SplitPane.Divider;
import webBoutique.Dao.ProduitDao;
import webBoutique.Metier.Produit;

@WebServlet("/Produit")
public class ProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProduitDao produitDao;
	
    public ProduitServlet() {
        super();
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// recuperation du dao initialisé par le listener
		this.produitDao = (ProduitDao)getServletContext().getAttribute("produitDao");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// fournir les produits a la page jsp
			
		request.setAttribute("produits", produitDao.findAll());
		getServletContext().getRequestDispatcher("/vues/produit/liste.jsp")
			.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
