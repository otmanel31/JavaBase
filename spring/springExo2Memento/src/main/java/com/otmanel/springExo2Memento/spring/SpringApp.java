package com.otmanel.springExo2Memento.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.otmanel.springExo2Memento.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
        
        
        
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}
