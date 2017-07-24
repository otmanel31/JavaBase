package ewo_web2_todoList.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import ewo_web2_todoList.Metier.Todo;

public class TodoDAO {
	private Connection connexion;
	
	private static final String SELECT_ALL_SQL = "SELECT * FROM todo";
	private static final String INSERT_ONE_SQL = "INSERT INTO todo (`description`, "
			+ "`priorité`, `contexte`, `finished`, `dateCreation`) VALUES(?,?,?,?, ?)";
	private static final String DELETE_ONE_SQL = "DELETE FROM todo WHERE id=?";
	private static final String SELECT_ONE_SQL = "SELECT * FROM todo WHERE id=?";
	private static final String UPDATE_ONE_SQL = " UPDATE todo SET `description`=?,"
			+ "`priorité`=?, `contexte`=?, `finished`=? WHERE `id`=?";
	
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
	public Todo findOne(int id) {
		try {
			Todo t = new Todo();
			select_one_statement.clearParameters();
			select_one_statement.setInt(1, id);
			ResultSet rs = select_one_statement.executeQuery();
			if (rs.next()) {
				t.setId(rs.getInt("id"));
				t.setDescription(rs.getString("description"));
				t.setPriorite(rs.getInt("priorité"));
				t.setContexte(rs.getString("contexte"));
				t.setFinished(rs.getBoolean("finished"));
				t.setDateCreation(rs.getTimestamp("dateCreation").toLocalDateTime());
			}else t = null;
			return t;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int save(Todo todo) {
		if(todo.getId() == 0) {
			// pour la date c juste a la creation que ce param existe pas la peine de le rajouter pour les modif
			try {
				insert_one_statement.clearParameters();
				insert_one_statement.setString(1, todo.getDescription());
				insert_one_statement.setInt(2, todo.getPriorite());
				insert_one_statement.setString(3, todo.getContexte());
				insert_one_statement.setBoolean(4, todo.isFinished());
				//insert_one_statement.setTimestamp(5, Timestamp.valueOf(todo.getDateCreation()));
				//insert_one_statement.setDate(5, new Date(Timestamp.valueOf(todo.getDateCreation()).getTime()));
				insert_one_statement.setDate(5, new Date(new java.util.Date().getTime()));
				return insert_one_statement.executeUpdate();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return 0;
	}
	
	public int delete(int id) {
		try {
			delete_one_statement.clearParameters();
			delete_one_statement.setInt(1, id);
			return delete_one_statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
