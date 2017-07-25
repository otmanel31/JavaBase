package designPatternFin;

import java.time.LocalDate;
import java.util.Iterator;

public class PeriodeTps implements Iterable<LocalDate> {
	private final LocalDate joursDebut;
	private final LocalDate joursFin;
	
	public PeriodeTps(LocalDate joursDebut, LocalDate joursFin) {
		this.joursDebut = joursDebut;
		this.joursFin = joursFin;
	}

	@Override
	public String toString() {
		return "PeriodeTps [joursDebut=" + joursDebut + ", joursFin=" + joursFin + "]";
	}

	@Override
	public Iterator<LocalDate> iterator() {
		return new PeriodIterator();
	}
	
	public class PeriodIterator implements Iterator<LocalDate>{

		private LocalDate currentDay;
		
		public PeriodIterator() {
			currentDay = joursDebut.minusDays(1);
		}
		@Override
		public boolean hasNext() {
			return currentDay.isBefore(joursFin);
		}

		@Override
		public LocalDate next() {
			currentDay = currentDay.plusDays(1);
			return currentDay;
		}
		
	}

}
