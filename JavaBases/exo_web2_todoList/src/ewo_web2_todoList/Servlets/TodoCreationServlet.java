package ewo_web2_todoList.Servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ewo_web2_todoList.Dao.TodoDAO;
import ewo_web2_todoList.Metier.Todo;

@WebServlet("/TodoCreation")
public class TodoCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDAO todoDao;
	
    public TodoCreationServlet() {
        super();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException{
    	super.init(config);
    	// recuperation du dao utilisé ds le listener
    	this.todoDao = (TodoDAO) getServletContext().getAttribute("todoDAO");
    	System.out.println("passage ds init de ma servlet todo");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// cas de la creation
			if (request.getParameter("todoId") == null) {
				//response.sendRedirect("TodoList");
				//return;
				Todo todo = new Todo();
				request.setAttribute("todo", todo);
				request.setAttribute("titre", "Formulaire de creation");
				request.setAttribute("btnForm", "Creer");
				getServletContext().getRequestDispatcher("/vues/todo/creation.jsp").forward(request, response);

			}else {
				// cas edition
				Todo todo = todoDao.findOne(Integer.parseInt(request.getParameter("todoId")));
				request.setAttribute("todo", todo);
				request.setAttribute("titre", "Formulaire d'edition");
				request.setAttribute("btnForm", "Editer");
				getServletContext().getRequestDispatcher("/vues/todo/creation.jsp").forward(request, response);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") == null) response.sendRedirect("TodoList");
		Todo todo = new Todo(
				Integer.parseInt(request.getParameter("id")),
				request.getParameter("description"),
				Integer.parseInt(request.getParameter("priorite")),
				request.getParameter("contexte"),
				Boolean.parseBoolean(request.getParameter("finished")),
				
				LocalDateTime.parse(request.getParameter("datetime").replaceAll(" ", "T"),DateTimeFormatter.ISO_LOCAL_DATE_TIME)
				);
		todoDao.save(todo);
		response.sendRedirect("TodoList");
	}

}
