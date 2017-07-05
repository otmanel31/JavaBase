package manipChaine;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "hello there";	
		String str2 = "hello you too ";	
		String str3 = "hello there";
		System.out.println( str3.charAt(2));
		System.out.println(str3.substring(2, 4)); 
		System.out.println(str3.indexOf("lo")); // positon indiquer debut
		
		char c =  str1.charAt(4);
		System.out.println("c => " + c + "est il un chiffre ?" + Character.isDigit(c));
		System.out.println("c "+ c + "est il une majuscule ?" + Character.isUpperCase(c));
		
		// les chaines de caracteres sont non modifiable / immuable
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Appuyer sur entree pour commencer : ");
		reader.nextLine();
		String buffer = "";
		/*for (int i=0; i< 100000; i++) {
			buffer += "ho";
		}*/
		//ATTENTITON
		// pour le smanipulationlourde de chaines NE PAS UTILISER DE STRING
		// cela peut etre lourd
		// Passer par StringBuilder plus performant
		StringBuilder sb = new StringBuilder("");
		for (int i =0; i < 100000; i++) {
			sb.append("ho");
		}
		System.out.println("terminé !! ");
		
	}

}
