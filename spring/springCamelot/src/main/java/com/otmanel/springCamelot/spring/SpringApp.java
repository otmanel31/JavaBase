package com.otmanel.springCamelot.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.otmanel.springCamelot.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
        
       IChevalier c1 = ctx.getBean("jacquouille", IChevalier.class);
       c1.partirEnQuete();
        
       IChevalier c2 = ctx.getBean("Godfroy", IChevalier.class);
       c2.getQuete().setDescription("voyager dsd le tps");
       c2.partirEnQuete();
       
        
       IChevalier c3 = ctx.getBean("Karadoc", IChevalier.class);
       c3.partirEnQuete();
       
       IChevalier c4 = ctx.getBean("Lancelot", IChevalier.class);
       c4.partirEnQuete();
        input.nextLine();
        
        IChevalier c5 = ctx.getBean("Link", IChevalier.class);
        c5.partirEnQuete();
        
         input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}
