package premierWeb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// deux maniere de declarer ine servelet ds une webapp
// ssoit par annotation
// soit via le fichier config web.xml
// les ervlet doivetn obligatoirzement hérité de httpServelet

@WebServlet("/HelloWorld") // permet de faire le mapping sans specifier ds le webXml
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HelloWorld() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		/// request contient tte les infos sur la requete http entrant
		/// objet response sert a construire la reponse html(ou autres) a renvoyer ds un navigateur
		response.setContentType("text/html");
		// recuperer un writer pour ecrire le contenu du html renvoyer
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head><title>Je suis un servlet </title></head>");
		pw.println("<body><h1>Hello world i m a servelt hiohihihihi "+ LocalDateTime.now()+
				" </h1></body>");
		pw.println("</html>");
		pw.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//doGet(request, response);
		if (request.getParameter("name") == null) response.sendRedirect("index.html");
		String nom = request.getParameter("name");
		String email = request.getParameter("email");
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head><title>Je suis un servlet esclave </title></head>");
		pw.println("<body><h1>Hello information user</h1><p>Nom: "+nom +" mail "+email +
				"</p><a href='index.html'>return home page</a></body>");
		pw.println("</html>");
		pw.close();
	}

}
