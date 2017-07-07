package metiers;

import interfaces.ICompteBancaire;

public class CompteSG implements ICompteBancaire, Comparable<ICompteBancaire> {

	private double solde;
	private String noCompte;
	
	public String getNoCompte() {
		return this.noCompte;
	}
	
	public void setNoCompte(String noCompte) {
		this.noCompte = noCompte;
	}
	
	public  CompteSG(double solde, String no) {
		this.solde = solde;
		setNoCompte(no);
	}
	
	@Override
	public boolean retirer(double montant) {
		if (montant < getSolde()) {
			this.solde -= montant;
			return true;
		}
		return false;
	}

	@Override
	public void deposer(double montant) {
		this.solde += montant;
		
	}

	@Override
	public double getSolde() {
		return this.solde;
	}

	@Override
	public String toString() {
		return "CompteSG [solde=" + solde + ", noCompte=" + noCompte + "]";
	}

	// renvoie - 1 si inferieur,  +1 si superieur ,et 0 si egal
	@Override
	public int compareTo(ICompteBancaire o) {
		if (getSolde() < o.getSolde()) return -1;
		else if (getSolde() > o.getSolde()) return 1;
		else return 0;
	}

}
