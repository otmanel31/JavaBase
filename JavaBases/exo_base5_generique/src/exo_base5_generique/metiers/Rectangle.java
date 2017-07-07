package exo_base5_generique.metiers;

public class Rectangle extends Figure{

	@Override
	public void display() {
		System.out.println("Je suis un " + this.getClass().getSimpleName());
	}

}
