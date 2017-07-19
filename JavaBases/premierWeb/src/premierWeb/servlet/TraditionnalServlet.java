package premierWeb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TraditionnalServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// recuperer un writer pour ecrire le contenu du html renvoyer
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head><title>Je suis un servlet traditional </title></head>");
		pw.println("<body><h1>tradition mais java8</h1></body>");
		pw.println("</html>");
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
}
