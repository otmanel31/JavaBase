package ewo_web2_todoList.Servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ewo_web2_todoList.Dao.TodoDAO;

@WebServlet("/TodoList")
public class TodoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TodoDAO todoDao;
    
    public TodoListServlet() {
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
		
    	request.setAttribute("todos", todoDao.findAll());
    	getServletContext().getRequestDispatcher("/vues/todo/todos.jsp").forward(request, response);
    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
