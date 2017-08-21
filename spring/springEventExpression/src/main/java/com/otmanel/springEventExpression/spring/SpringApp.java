package com.otmanel.springEventExpression.spring;

import java.util.*;

import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.otmanel.springEventExpression.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
        
        Client c1 = ctx.getBean("client1", Client.class);
        System.out.println(c1);
        Client c2 = ctx.getBean("client2", Client.class);
        System.out.println(c2);
        Client c3 = ctx.getBean("client3", Client.class);
        System.out.println(c3);
        Client c4 = ctx.getBean("client4", Client.class);
        System.out.println(c4);
        Client c5 = ctx.getBean("client5", Client.class);
        System.out.println(c5);
        
        Site s1 = ctx.getBean("s1", Site.class);
        System.out.println(s1);
        Site s2 = ctx.getBean("s2", Site.class);
        System.out.println(s2);
        
        ctx.publishEvent(new MyCustomEvent(s2, "hello from main"));
        
        input.nextLine();
        
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}
