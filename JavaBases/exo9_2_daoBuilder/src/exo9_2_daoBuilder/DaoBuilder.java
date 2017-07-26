package exo9_2_daoBuilder;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		generate();
	}
	
	public void generate(){
		String tableName = this.getClassName().getSimpleName().toLowerCase();
		Method[] methods;
		methods = this.getClassName().getDeclaredMethods();
		Arrays.asList(methods).stream().forEach(f->{
			if (f.getName().matches("^get[A-Z].*") && !f.getReturnType().equals(void.class))
				System.out.println(f.getName().substring(3).toLowerCase());
			if( f.getName().matches("is[A-Z].*") && !f.getReturnType().equals(void.class))
				System.out.println("bool " + f.getName().substring(2).toLowerCase());
		});
		//Arrays.asList(methods).stream().forEach(f-> f.toString().substring(3));
		//System.out.println(tableName);
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
	
	/*public static class Builder{
	private Class buildClassName;
	private Connection connexion;
	private String buildPrimaryKey;
	private List<String> attributs;
	
	public Builder(Class className, Connection connexion, String primaryKey){
		this.buildClassName = className;
		this.connexion = connexion;
		this.buildPrimaryKey = primaryKey;
		this.attributs = new ArrayList<>();
	}
	
	
	
	public DaoBuilder build(){
		return null;
	}
	
	}
	 */
}
