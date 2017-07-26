package exo9_2_daoBuilder;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Program {

	public static void main(String[] args) {
		try {
			DaoBuilder todoDao = new DaoBuilder(Todo.class , 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/tptodo", "root",""), 
					"id");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
