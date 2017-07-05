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
		
		System.out.println(chiffreRomain(55));
		System.out.println(chiffreRomain(100));
		System.out.println(chiffreRomain(18));

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
	public static String chiffreRomain(int number) {
		/*
		 * 1 ->  I		10 X 	100 c
		 * 2->   II		20 XX	200 cc
		 * 3 ->	 III	30 XXX	300 CCC
		 * 4 ->	 IV		40 XL	400 CD
		 * 5->	V		50 L	500 D
		 * 6->	VI		60 LX	600 DC
		 * 7->	VII		70 LXX	700 DCC
		 * 8->	VII		80 LXXX	800 DCC
		 * 9->	IX		90 XC	900 CM
		 * 10->	X		100 C	1000 M
		 */
		int centaine = (number / 100) % 10;  // 521 -> 5
		int dizaine = (number / 10) % 10; // 521 --> 52 % 10 -> 2
		int unite = number % 10; // 521 % 10 -> 1
		return  sousChiffreRomain(centaine, new char[] {'C', 'D', 'M'})
						+ sousChiffreRomain(dizaine, new char[] {'X', 'L', 'C'})
						+ sousChiffreRomain(unite, new char[] {'I', 'V', 'X'});
	}
	public static String sousChiffreRomain(int number, char[] sigles) {
		switch (number) {
			case 0: return "";
			case 1: return "" + sigles[0];
			case 2: return "" + sigles[0] + sigles[0];
			case 3: return "" + sigles[0] + sigles[0] + sigles[0];
			case 4: return "" + sigles[0] + sigles[1];
			case 5: return "" + sigles[1];
			case 6: return "" + sigles[1] + sigles[0];
			case 7: return "" + sigles[1] + sigles[0] + sigles[0];
			case 8: return "" + sigles[1] + sigles[0] + sigles[0] + sigles[0];
			case 9: return "" + sigles[0] + sigles[2];
		
		}
		return "";
	}

}
