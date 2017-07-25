package designPattern;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		System.out.println("debut programme");
		
		Adresse a1 = new Adresse("221 baker street", "Londres", "Angleterre");
		Client c1 = new Client("Sherlock Homes", a1);
		Client c2 = new Client("Jon Watson", a1);
		System.out.println(c1);
		System.out.println(c2);
		c2.setAdresse(new Adresse("courbep", "France", "freé"));
		System.out.println(c2);
		/* debut singleton
		CustomConfig cc = CustomConfig.getInstance();
		
		System.out.println(cc);
		
		Scanner reader= new Scanner(System.in);
		System.out.println("appuyer sur entrree ");
		reader.nextLine();
		CustomConfig cc2 = CustomConfig.getInstance();
		System.out.println(cc2);
		cc.addValueTodConfig("couleur", "noir");
		cc.addValueTodConfig("dbName", "test");
		cc.addValueTodConfig("dbUser", "user");
		cc.addValueTodConfig("dbPwd", "*****");
		cc.saveToPropertiesFile("config.properties");
		cc.loadFromPropertiesFile("config.properties");
		System.out.println(cc.getValueFronConfig("couleur"));
		fin singleton
		*/
	}

}
