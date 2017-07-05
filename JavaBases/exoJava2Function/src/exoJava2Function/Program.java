package exoJava2Function;

import java.util.Arrays;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("function1 => "+ function1(12.5,14.0,13.5, 0, 0));
		System.out.println("reverse fnction => " + reverse("bonjour"));
		
		System.out.println("is palindrome ==> "+ isPalindrome("madam"));
		System.out.println("is palindrome ==> "+ isPalindrome("madama"));
		afficher("test micro");
		

	}
	public static  int function1(double ... values ) {
		
		int compteur = 0;
		for (double v: values) {
			if( v > 0) {
				compteur += 1;
			}
		}
		return compteur;
	}
	
	public static String reverse(String txt) {
		//return new StringBuilder(txt).reverse().toString();
		char[] in = txt.toCharArray();
		char temp;
		int end = in.length-1;
		int compteur = 0;
		String txt2 ="";
		for (int i=in.length-1; i>=0; i--) {
			txt2 += in[i];
		}
		return txt2;
	}
	public static boolean isPalindrome(String txt) {
		String reverse = "";
		
		for (int i=txt.length()-1; i>=0; i--) {
			reverse += txt.charAt(i);
		}
		if (txt.equals(reverse)) return true;
		else return false;
	}
	
	public static void afficher(String txt) {
		char[] voyelle = {'a','e','i','o','u','y'};
		double voyel = 0;

		String[] mots;
		for (int i=0; i< txt.length(); i++) {
			for (char t : voyelle) {
				if (txt.charAt(i) == t) voyel+=1;
			}
			
		} 
		
		mots = txt.split("\\s+");
		System.out.println("% voyelle ==> "+ (voyel/txt.length())*100 +" nbr voyelle => "+voyel);
		System.out.println("nbre mots dans le txt ==> " + mots.length);
	}
	public static void display(int i) {
		char[] chiffreRomain = {'I','V','X', 'L'};
		int[] chiffres = {1,2,3,4,5,6,7,8,9};

		if (i >0 && i< 10) {
			
		}
		
	}

}
