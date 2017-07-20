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
	private Connection connection;
	// mes requetes paramétrées
	private PreparedStatement select_all_statement;
	
	public ProduitDao(Connection connection) {
		this.connection = connection;
		try {
			select_all_statement = connection.prepareStatement(SELECT_ALL_SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
}
