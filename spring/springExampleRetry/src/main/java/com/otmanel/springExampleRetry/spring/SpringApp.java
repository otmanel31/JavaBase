package com.otmanel.springExampleRetry.spring;

import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.otmanel.springExampleRetry.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
        
        DocumentBuilderFactory dbf = ctx.getBean("dbf", DocumentBuilderFactory.class);
        DocumentBuilder db = ctx.getBean("db", DocumentBuilder.class);
        Document d = ctx.getBean("d", Document.class);
        
        NodeList list = ctx.getBean("node", NodeList.class);
        for (int i = 0; i < list.getLength(); i++)
		{
			Element el = (Element) list.item(i);
			System.out.println("element -> " + el.getNodeName());
			System.out.println("value -> " + el.getFirstChild().getNodeValue());
		}
        
        /* A FAIRE LE X PATH */
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}
