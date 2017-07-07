package exo_base3_oop;

import exo_base3_oop.metier.Article;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hey Hello ------------------------------------------------------------");
		Article a1 = new Article();
		System.out.println(a1.toString());
		Article a2 = new Article("otman","otmannnn", "azertyuiop", 5.0);
		System.out.println(a2.toString());
		Article a3 = new Article();
		System.out.println(a3.toString());
		Article a4 = new Article("o","ot", "azertyuiop", 6.0);
		System.out.println(a4.toString());
	}

}
