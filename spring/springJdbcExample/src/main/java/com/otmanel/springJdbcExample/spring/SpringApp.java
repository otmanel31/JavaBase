package com.otmanel.springJdbcExample.spring;

import java.sql.DriverManager;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.otmanel.springJdbcExample.beans.*;
import com.otmanel.springJdbcExample.dao.ProduitDao;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        //DriverManagerDataSource
        //JdbcTemplate
        input.nextLine();
        System.out.println("--------------------------------------");
        
        ProduitDao dao = ctx.getBean("produitDao", ProduitDao.class);
        List<Produit> produits = dao.findAll();
        for( Produit p : produits) System.out.println(p);
        
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}
