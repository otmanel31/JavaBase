package premierWeb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.regexp.internal.REUtil;

@WebServlet("/Aiguillage")
public class AiguillageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AiguillageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request dispatcher parmet de renvoyer vers une vue
		// getServletContext == acces au context global de la WEB APP soit all files/ servlets
		getServletContext().getRequestDispatcher("/vues/form.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("nom") == null) response.sendRedirect("/Aiguillage");
		// ici preparation des donnees
		String nom = request.getParameter("nom");
		String email = request.getParameter("email");
		String commentaire = request.getParameter("commentaire");
		int note = Integer.parseInt(request.getParameter("note"));
		// passage des donnees a la vue
		// => set attribute permet dattacher nimporte quel objet dinnee sous un nom cjoisi
		// pour qu ela page jsp en fasse usage
		request.setAttribute("nom", nom);
		request.setAttribute("email", email);
		request.setAttribute("commentaire", commentaire.replaceAll("\n", "<br/>"));
		System.out.println(commentaire);
		// ici choix de la vue
		if(note <=0 ) {
			getServletContext().getRequestDispatcher("/vues/problems.jsp")
				.forward(request, response);
		}
		else if (note <=2 ) getServletContext().getRequestDispatcher("/vues/merci.jsp")
			.forward(request, response);
		else getServletContext().getRequestDispatcher("/vues/menber.jsp")
			.forward(request, response);
	}

}
