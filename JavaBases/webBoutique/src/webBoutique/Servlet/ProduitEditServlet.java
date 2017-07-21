package webBoutique.Servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webBoutique.Dao.ProduitDao;
import webBoutique.Metier.Produit;

@WebServlet("/ProduitEdit")
public class ProduitEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProduitDao produitDao;
	
	

    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.produitDao = (ProduitDao)getServletContext().getAttribute("produitDao");
	}

	public ProduitEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//  si pas di c une demande de formulaire dze creartion
		if (request.getParameter("produitId") == null ) {
			Produit p = new Produit();
			request.setAttribute("produit", p);
			request.setAttribute("titre", "creation produit");
			getServletContext().getRequestDispatcher("/vues/produit/form.jsp").forward(request, response);
			
		}
		else {
			// on editte un produit exisant
			Produit p = produitDao.findOne(Integer.parseInt(request.getParameter("produitId")));
			if (p == null) response.sendRedirect("Produit");
			request.setAttribute("titre", "Edition produit");
			request.setAttribute("produit", p);
			getServletContext().getRequestDispatcher("/vues/produit/form.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") == null) response.sendRedirect("Produit");
		Produit p = new Produit(
					Integer.parseInt(request.getParameter("id")),
					request.getParameter("nom"),
					Double.parseDouble(request.getParameter("prix")),
					Double.parseDouble(request.getParameter("poid"))
				);
		// pour sauver le produit en base
		produitDao.saveProduit(p);
		response.sendRedirect("Produit");
		
	}

}
