package manipTableau;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ici declaration d'un tableau dentier - declaration reference donc non alloue
		int[] tableau1;
		// pour allouer un tableau il faut utiliser le mot clé new et specifier la 
		// taille du tableai
		tableau1 = new int[5];
		
		tableau1[0] = 12;
		tableau1[2] = -10;
		System.out.println(Arrays.toString(tableau1));
		// Arrays = => classe permettznt la manip des tableau
		// sort, tostring etc

		double[] tab = {2, 2.5, 5, 68};
		// acceder en dehors du tableau provoque une erreur
		String[] villes = {"toulouse","Paris", "Marseille"};
		String[] villesExtended = Arrays.copyOf(villes, 5);
		
		// TABLEAU MULTIDIMENSIONNELLE;
		double[][] matrice = {
				{1,2,4},
				{4,5,6},
				{7,8,9},
				{1,2,3,4,5,6}
		};
		System.out.println("matrice ==> "+ matrice[1][2]);
		// poissbilité dallocation 
		double[][] matrice2 = new double[4][6];
		// colonne non aloué ==> a ns dze dle faire
		double[][] matrice3 = new double[3][];
		matrice[0] = new double[5];
	}

}
