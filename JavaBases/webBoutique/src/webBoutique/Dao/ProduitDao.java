package webBoutique.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import webBoutique.Metier.Produit;

public class ProduitDao {
	public static final String SELECT_ALL_SQL = "SELECT * FROM produit";
	public static final String INSERT_ONE_SQL = "INSERT INTO produit (`nom`, `prix`, `poid`) VALUES"
			+ "(?,?,?)";
	public static final String SELECT_BY_ID_SQL = "SELECT * FROM produit WHERE id=?";
	public static final String UPDATE_ONE_SQL = "UPDATE produit SET `nom`=?, `prix`=?, `poid`=?"
			+ "WHERE id=?";
	public static final String DELETE_ONE_SQL = "DELETE FROM produit WHERE id=?";
	
	private Connection connection;
	// mes requetes paramétrées
	private PreparedStatement select_all_statement;
	private PreparedStatement insert_one_statement;
	private PreparedStatement update_one_statement;
	private PreparedStatement select_by_id_statement;
	private PreparedStatement delete_one_statement;

	public ProduitDao(Connection connection) {
		this.connection = connection;
		try {
			select_all_statement = connection.prepareStatement(SELECT_ALL_SQL);
			insert_one_statement = connection.prepareStatement(INSERT_ONE_SQL);
			update_one_statement = connection.prepareStatement(UPDATE_ONE_SQL);
			select_by_id_statement = connection.prepareCall(SELECT_BY_ID_SQL);
			delete_one_statement = connection.prepareStatement(DELETE_ONE_SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int deleteProduit(int id) {
		try {
			delete_one_statement.clearParameters();
			delete_one_statement.setInt(1, id);
			return delete_one_statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Produit findOne(int id) {
		try {
			Produit p = new Produit();
			select_by_id_statement.clearParameters();
			select_by_id_statement.setInt(1, id);
			ResultSet rs = select_by_id_statement.executeQuery();
			// on va se postionner sur la premiere ligne
			if (rs.next()) {
				p.setId(rs.getInt("id"));
				p.setNom(rs.getString("nom"));
				p.setPrix(rs.getDouble("prix"));
				p.setPoid(rs.getDouble("poid"));
			}
			else p = null;
			rs.close();
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // pas de rpoduit trouvé
	}
	
	public List<Produit> findAll(){
		List<Produit> produits = new ArrayList<>();
		try {
			
			ResultSet res = this.select_all_statement.executeQuery();
			
			while(res.next()) {
				// parcourir les lignes du resultats
				produits.add(new Produit(res.getInt("id"), res.getString("nom"), 
						res.getDouble("prix"), res.getDouble("poid")));
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produits;
	}
	public int saveProduit(Produit p) {
		// si id est a 0 la clé primaire nest pas encore renseign et cela veut dire que c un nouveau
		// produit a inserer ds la base sinon c un produit concernant un ligne deja ds la base donc
		// c un update
		if (p.getId() == 0) {
			try {
				// nettoyer les params dune execution prcedente sil y en a
				insert_one_statement.clearParameters();
				insert_one_statement.setString(1, p.getNom());
				insert_one_statement.setDouble(2, p.getPrix());
				insert_one_statement.setDouble(3, p.getPoid());
				return insert_one_statement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		else {
			try {
				update_one_statement.clearParameters();
				update_one_statement.setString(1, p.getNom());
				update_one_statement.setDouble(2, p.getPrix());
				update_one_statement.setDouble(3, p.getPoid());
				update_one_statement.setInt(4, p.getId());
				return update_one_statement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0; // aucune ligne na été executé
	}
}
