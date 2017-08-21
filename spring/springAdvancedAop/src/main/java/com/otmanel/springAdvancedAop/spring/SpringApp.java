package com.otmanel.springAdvancedAop.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.otmanel.springAdvancedAop.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
        
        ITextUtils tu = ctx.getBean("tu", ITextUtils.class);
        String entree = "   bonjour le monde     ";
        //entree +="sqjvhsklj<hvsklhvsjkl<hvklsj<hvksjl<hvkls<jdvhmsk<hvsljnvmksjhgmskl<nvbsl<hlms<nvlshvls<kvlswblswnblsfwkslwkslwkvsbslknvblxknvklxfwvbklsfvbslwfkvsfklnbslfkbnslfknb";
        String result=  tu.inversion(entree);
        int r = tu.compterVoyelle(entree);
        System.out.println("inversion " + result + " -- nb voyelle " + r);
        
        result = tu.inversion(null);
        
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}
