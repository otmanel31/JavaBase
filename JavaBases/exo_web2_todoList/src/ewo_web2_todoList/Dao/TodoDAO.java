package ewo_web2_todoList.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import ewo_web2_todoList.Metier.Todo;

public class TodoDAO {
	private Connection connexion;
	
	private static final String SELECT_ALL_SQL = "SELECT * FROM todo";
	private static final String INSERT_ONE_SQL = "INSERT INTO todo (`description`, "
			+ "`priorité`, `contexte`, `finished`, `dateCreation`) VALUES(?,?,?,?)";
	private static final String DELETE_ONE_SQL = "DELETE FROM todo WHERE id=?";
	private static final String SELECT_ONE_SQL = "SELECT * FROM todo WHERE id=?";
	private static final String UPDATE_ONE_SQL = " UPDATE todo SET `description`=?,"
			+ "`priorité`=?, `contexte`=?, `finished`=?, `dateCreation`=?";
	
	private PreparedStatement select_all_statement;
	private PreparedStatement select_one_statement; 
	private PreparedStatement insert_one_statement;
	private PreparedStatement delete_one_statement;
	private PreparedStatement update_one_statement;
	
	public TodoDAO(Connection connexion) {
		this.connexion = connexion;
		try {
			select_all_statement = connexion.prepareStatement(SELECT_ALL_SQL);
			select_one_statement = connexion.prepareStatement(SELECT_ONE_SQL);
			insert_one_statement = connexion.prepareStatement(INSERT_ONE_SQL);
			delete_one_statement = connexion.prepareStatement(DELETE_ONE_SQL);
			update_one_statement = connexion.prepareStatement(UPDATE_ONE_SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Todo> findAll(){
		List<Todo> todos = new ArrayList<>();
		try {
			ResultSet res = select_all_statement.executeQuery();
			while(res.next()) {
				//res.getDate("").toInstant();
				todos.add(new Todo(res.getInt("id"), res.getString("description"),
						res.getInt("priorité"), res.getString("contexte"), 
						res.getBoolean("finished"),
						//LocalDateTime.ofEpochSecond(, nanoOfSecond, offset), 0, offset)
						res.getTimestamp("dateCreation").toLocalDateTime()));
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return todos;
	}
}
