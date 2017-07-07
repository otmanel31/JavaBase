package exo_base5_generique.metiers;

import exo_base5_generique.interfaces.IAffichable;

public abstract class Figure implements IAffichable{

	@Override
	public String toString() {
		return "Figure [getClass()=" + getClass().getSimpleName()  + "]";
	}
	
}
