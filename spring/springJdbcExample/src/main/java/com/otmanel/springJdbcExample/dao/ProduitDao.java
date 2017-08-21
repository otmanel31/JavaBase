package com.otmanel.springJdbcExample.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.otmanel.springJdbcExample.beans.Produit;

public class ProduitDao implements RowMapper<Produit>{
	
	public static final String findAllSql =" select * from produits";
	public static final String findById =" select * from produits where id=?";
	public static final String updateSql =" update produits set nom=?, poids=?, prix=? where id=?";
	public static final String insertSql =" insert into produits(nom, poids, prix) VALUES(?,?,?)";
	
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {return jdbcTemplate;}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}
	
	@Override
	public Produit mapRow(ResultSet arg0, int arg1) throws SQLException {
		return new Produit(	arg0.getInt("id"), arg0.getString("nom"),
				arg0.getDouble("poids"), arg0.getDouble("prix"));
	}
	public List<Produit> findAll(){
		return jdbcTemplate.query(findAllSql, this);
	}
	
	public Produit foindById(int id) {
		return jdbcTemplate.queryForObject(findById, new Object[] {id}, this);
	}
	public int save(Produit p) {
		if (p.getId() == 0) {
			return jdbcTemplate.update(insertSql, p.getNom(), p.getPoids(), p.getPrix());
		}
		else
			return jdbcTemplate.update(updateSql, p.getNom(), p.getPoids(), p.getPrix(), p.getId());
	}
}
