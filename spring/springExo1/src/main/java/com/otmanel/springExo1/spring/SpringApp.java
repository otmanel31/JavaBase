package com.otmanel.springExo1.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.otmanel.springExo1.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
        
        IArtitste ia1 = ctx.getBean("mozart", IArtitste.class);
        ia1.faireUnNumero();
        IArtitste ia2 = ctx.getBean("soldat", IArtitste.class);
        ia2.getInstrument().setNom("trompette de engagé balein");
        ia2.faireUnNumero();
        IArtitste ia3 = ctx.getBean("soldat2", IArtitste.class);
        ia3.faireUnNumero();
        IArtitste j = ctx.getBean("jongleur", IArtitste.class);
        j.faireUnNumero();
        
        IArtitste ha = ctx.getBean("intru", IArtitste.class);
        ha.faireUnNumero();
        
        IArtitste vl = ctx.getBean("violoniste", IArtitste.class);
        vl.faireUnNumero();
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}
