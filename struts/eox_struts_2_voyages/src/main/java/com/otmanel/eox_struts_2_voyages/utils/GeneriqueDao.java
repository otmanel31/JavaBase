package com.otmanel.eox_struts_2_voyages.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.otmanel.eox_struts_2_voyages.utils.QueryBuilder.TypeWhere;

public class GeneriqueDao<T> {
	private Connection connexion;
	// nom champ -> infos sur les champs
	private Map<String, fieldInfo> champs;
	private String primaryKeyName;
	//class de lentité
	private Class<T> entityClass;
	
	// requetes a initialisé
	private PreparedStatement findAllStatement;
	private PreparedStatement findOneStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteAllStatement;
	
	public GeneriqueDao(Connection connexion, Map<String, fieldInfo> champs, String primaryKeyName,
			Class<T> entityClass) {
		this.connexion = connexion;
		this.champs = champs;
		this.primaryKeyName = primaryKeyName;
		this.entityClass = entityClass;
		QueryBuilder qb = new QueryBuilder(entityClass.getSimpleName(), connexion);
		// champ.keyset -> je parcours te les cle ds ma  map
		for(String fieldName : champs.keySet()) {
			// aout des champs
			qb.addSelectedField(fieldName);
		}
		this.findAllStatement = qb.find().build();
		qb.addWhereClause(primaryKeyName, TypeWhere.EQUAL, 1);
		this.findOneStatement = qb.find().build();
		
		qb.reset();
		// pour insert ajour des champs sauf clé primaire
		for(String fieldName : champs.keySet()) {
			// aout des champs
			// si nom champ nest pas egal a id -> car ds inset on ajoute pas id car déja en autoincereemt
			if (!fieldName.equals(primaryKeyName))
				qb.addSelectedField(fieldName);
		}
		this.insertStatement = qb.insert().build();
		// le wherre est le dernier ajouter
		// ex -> si update nom, prix, poid, set nom = ? ..... where id =? -> 4 champ = idest a position 4
		qb.addWhereClause(primaryKeyName, TypeWhere.EQUAL, champs.size());
		this.updateStatement = qb.update().build();
		qb.reset();
		qb.addWhereClause(primaryKeyName, TypeWhere.EQUAL, 1);
		this.deleteAllStatement = qb.delete().build();
	}

	private T fillEntityFromRow(ResultSet res) {
		// je creer un objet vide 
		try {
			T entity =  this.entityClass.newInstance();
			// je parcours mes champs
			for(String fieldName : this.champs.keySet()) {
				// je recup le field info correspondant
				fieldInfo f = this.champs.get(fieldName);
				if (f.typeChamp.equals(int.class)) 
					f.setter.invoke(entity, res.getInt(fieldName));
				else if (f.typeChamp.equals(double.class)) 
					f.setter.invoke(entity, res.getDouble(fieldName));
				else if (f.typeChamp.equals(String.class)) 
					f.setter.invoke(entity, res.getString(fieldName));
				else if (f.typeChamp.equals(boolean.class)) 
					f.setter.invoke(entity, res.getBoolean(fieldName));
				else if(f.typeChamp.equals(java.util.Date.class))
					f.setter.invoke(entity, res.getDate(fieldName));
			}
			return entity;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public List<T> findAll(){
		List<T> datas = new ArrayList<>();
		
		try {
			ResultSet res = this.findAllStatement.executeQuery();
			while(res.next()) datas.add(fillEntityFromRow(res));
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datas;
		
	}
	
	public T findById(int key) {
		T data = null;
		try {
			this.findOneStatement.clearParameters();
			this.findOneStatement.setInt(1, key);
			ResultSet res = findOneStatement.executeQuery();
			if (res.next()) data = fillEntityFromRow(res);
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public int save(T entity) {
		try {
			int id = (int)this.champs.get(primaryKeyName).getter.invoke(entity);
			if (id == 0) {
				this.insertStatement.clearParameters();
				//insertion
				fillRequestFromEntity(entity, this.insertStatement);
				return this.insertStatement.executeUpdate();
			}else {
				// update
				this.updateStatement.clearParameters();
				int pos = fillRequestFromEntity(entity, this.updateStatement);
				this.updateStatement.setInt(pos, id);
				return this.updateStatement.executeUpdate();
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private int fillRequestFromEntity(T entity, PreparedStatement p)
			throws SQLException, IllegalAccessException, InvocationTargetException {
		int position = 1;
		// remplir la requete a	vc les valeur de l objet
		for (String fieldName : champs.keySet()) {
			// ne paq inserer le champ cdle primaire
			if (!fieldName.equals(primaryKeyName)) {
				fieldInfo fi = this.champs.get(fieldName);
				if (fi.typeChamp.equals(int.class)) {
					p.setInt(position++, (int)fi.getter.invoke(entity));
				}
				else if(fi.typeChamp.equals(double.class)) {
					p.setDouble(position++, (double)fi.getter.invoke(entity));
				}
				else if(fi.typeChamp.equals(boolean.class)) {
					p.setBoolean(position++, (boolean)fi.getter.invoke(entity));
				}
				else if(fi.typeChamp.equals(String.class)) {
					p.setString(position++, (String)fi.getter.invoke(entity));
				}
				else if(fi.typeChamp.equals(Date.class)) p.setDate(position++, new java.sql.Date(((Date)fi.getter.invoke(entity)).getTime()));
			}
		}
		return position;
	}
	
	public int delete(int id) {
		try {
			this.deleteAllStatement.clearParameters();
			this.deleteAllStatement.setInt(1, id);
			return this.deleteAllStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static class fieldInfo{
		private final String nom;
		private final Method getter;
		private final Method setter;
		private final Class typeChamp;
		// si attrr final impossible de generer des setters
		public String getNom() {return nom;}
		public Method getGetter() {return getter;}
		public Method getSetter() {return setter;}
		public Class getTypeChamp() {return typeChamp;}
		
		public fieldInfo(String nom, Method getter, Method setter, Class typeChamp) {
			this.nom = nom;
			this.getter = getter;
			this.setter = setter;
			this.typeChamp = typeChamp;
		}
		
	}
	
	public static class GenericDaoBuilder<T>{
		private Class<T> buildEntityClass;
		private Connection buildConnection;
		private String buildPrimaryKeyName;
		private List<fieldInfo> buildChamps;
		// constructeuir du builder 
		// ex -> quand kje l"appele GenericBuilder.. bp = new GenericDao.GenericDaoBuilder(Porduit.class, connect ...)
		public GenericDaoBuilder(Class<T> entity, Connection c, String primaryKeyName) {
			this.buildEntityClass = entity;
			this.buildConnection = c;
			this.buildPrimaryKeyName = primaryKeyName;
			this.buildChamps = new ArrayList<>();
		}
		
		private void fillFieldInfos() {
			Method[] methods = this.buildEntityClass.getMethods();
			for( Method mgetter : methods) {
				// examinons chaque methodes
				if( mgetter.getName().matches("^(get|is)[A-Z][A-Za-z0-9_]+$") && mgetter.getParameterTypes().length == 0 
						&&( mgetter.getReturnType().equals(int.class) || mgetter.getReturnType().equals(double.class)
						|| mgetter.getReturnType().equals(boolean.class) || mgetter.getReturnType().equals(Date.class) ||
						mgetter.getReturnType().equals(String.class))) {
					String fieldName = null;
					if (mgetter.getName().startsWith("get")) fieldName = mgetter.getName().substring(3,4).toLowerCase() + 
							mgetter.getName().substring(4);
					else if (mgetter.getName().startsWith("is")) fieldName = mgetter.getName().substring(2,3).toLowerCase() + 
							mgetter.getName().substring(3);
					
					// recuperation du ssetter 
					try {
						Class typeChamp = mgetter.getReturnType();
						Method msetter = buildEntityClass.getMethod(mgetter.getName().replaceFirst("(get|is)", "set"), 
								mgetter.getReturnType());
						// si jarrrive ici tt est ok -
						// - nous avons un  getter      ->mgetter
						// - un setter coreespondant 	->msetter
						// - le nioùm du cjhamp correspondant -> fillName
						this.buildChamps.add(new fieldInfo(fieldName, mgetter, msetter, typeChamp));
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		public GeneriqueDao<T> build(){
			// recuperreation de ts les champs
			fillFieldInfos();
			final Map<String, fieldInfo> champsInfos = new HashMap<>();
			this.buildChamps.stream().forEach(f->champsInfos.put(f.getNom(), f));
			return new GeneriqueDao<>(buildConnection, champsInfos, buildPrimaryKeyName, buildEntityClass);
		}
	}
}
