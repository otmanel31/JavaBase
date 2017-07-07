package interfaces;

public interface ICompteBancaire {
	// dans interface, pas d'etat ni de code
	// quand on declaere une methode elle est automatiquement abstract et publique
	// charge a celui qui implemente linterface de fournir le code correspondant
	boolean retirer(double montant);
	void deposer(double montant);
	double getSolde();
}
