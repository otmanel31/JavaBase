package exo9_2_daoBuilder;

import java.sql.Connection;

public class DaoBuilder implements IDao{
	private Class className;
	private Connection connexion;
	private String primaryKeyName;
	
	public Class getClassName() {return className;}
	public void setClassName(Class className) {this.className = className;}
	public Connection getConnexion() {return connexion;}
	public void setConnexion(Connection connexion) {this.connexion = connexion;}
	public String getPrimaryKeyName() {return primaryKeyName;}
	public void setPrimaryKeyName(String primaryKeyName) {this.primaryKeyName = primaryKeyName;}
	
	public DaoBuilder(Class className, Connection connexion, String primaryKeyName) {
		setClassName(className);
		setConnexion(connexion);
		setPrimaryKeyName(primaryKeyName);
	}
	
	public void ignoreChamp() {
		
	}
	@Override
	public String findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String findById() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String remove() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
	
	
}
