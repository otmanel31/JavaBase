package exo_base5_generique.metiers;

import exo_base5_generique.interfaces.IAffichable;

public class Label implements IAffichable{
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Label(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Label [message=" + message + "]";
	}

	@Override
	public void display() {
		System.out.println("");
	}
	
}
