import java.util.Arrays;
import java.util.Random;

import interfaces.ICompteBancaire;
import metiers.CompteSG;

public class Program {

	public static void main(String[] args) {
		ICompteBancaire  c1 = new CompteSG(250, "46464671");
		// c1 nexpose que les fonctionnalité generique de linterfance
		
		CompteSG sg1 = new CompteSG(500, "16467164");
		CompteSG sg2 = new CompteSG(500, "546546546546");
		transferer(sg1, sg2, 200);
		
		ICompteBancaire[] comptes = new ICompteBancaire[5];
		
		// generator aleatoire
		Random rd = new Random();
		
		for (int i = 0; i <5; i++) {
			comptes[i] = new CompteSG(rd.nextDouble() * 1000.0, "100000"+i);
		}
		System.out.println("avant tri ---------------------------");
		for (ICompteBancaire ic: comptes) {
			System.out.println(ic);
		}
		System.out.println("apres tri ---------------------------");
		Arrays.sort(comptes);
		for (ICompteBancaire ic: comptes) {
			System.out.println(ic);
		}
	}

	public static boolean transferer(ICompteBancaire src, ICompteBancaire dest, double montant) {
		if (src.retirer(montant)) {
			dest.deposer(montant);
			return true;
		}
		return false;
	}
}
