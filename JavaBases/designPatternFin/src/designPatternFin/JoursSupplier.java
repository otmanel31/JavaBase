package designPatternFin;

import java.time.LocalDate;
import java.util.function.Supplier;

public class JoursSupplier implements Supplier<LocalDate> {
	private final LocalDate joursDebut;
	private LocalDate joursCourant;
	
	public JoursSupplier(LocalDate joursDebut) {
		super();
		this.joursDebut = joursDebut;
		this.joursCourant = joursDebut.minusDays(1);
	}

	@Override
	public LocalDate get() {
		this.joursCourant = this.joursCourant.plusDays(1);
		return this.joursCourant;
	}

	@Override
	public String toString() {
		return "JoursSupplier [joursDebut=" + joursDebut + ", joursCourant=" + joursCourant + "]";
	}
	
	
	
	
}
	
