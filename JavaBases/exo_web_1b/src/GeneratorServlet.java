import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Generator")
public class GeneratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GeneratorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/vues/form.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String taille = request.getParameter("taille");
		
		if (type == null || taille == null ) response.sendRedirect("Generator");
		
		try {
			int t = Integer.parseInt(taille);
			switch (Integer.parseInt(type)) {
			case 0:
				request.setAttribute("taille", t);
				getServletContext().getRequestDispatcher("/vues/triangle.jsp").forward(request, response);
				for(int i = 0; i <Integer.parseInt(taille); i++) {
					//colonne
					if(i == 0) System.out.println("");
					else System.out.println("*");
					for( int j = i ; j < t; j++ ) System.out.print("*");
				}
				break;
			case 1:
				request.setAttribute("taille", t);
				getServletContext().getRequestDispatcher("/vues/carree.jsp").forward(request, response);
				for (int i = 0; i < t; i++) {
					System.out.print("*");
					for (int j =0; j< t; j++) {
						if (j == (t-1)) System.out.println("");
						else System.out.print("*");
					}
				}
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
