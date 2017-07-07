package javaGenerique.metier;

/*
 * couple representant un ensemble de 2 valeurs
 * ic t1 et t2 sont de splaceholder, il seront remplacer par les variables de type choisi
 * lors de la declaration concrete dun tuple
 */

// JE RAJOUTE UNE CONTRAINTE SUR T2 -- > il doit implementer Comparable
// !!!!!  ON MET EXTENDS AU LEU DE IMPLEMENT POUR LUI DIRE QUE C UNE CONTRAINTE
public class Tuple<T1, T2 extends Comparable<T2>> implements  Comparable<Tuple<T1,T2>>{
	private T1 value1;
	private T2 value2;
	
	public T1 getValue1() {return value1;}
	public void setValue1(T1 value1) {
		this.value1 = value1;
	}
	
	public T2 getValue2() {return value2;}
	public void setValue2(T2 value2) {
		this.value2 = value2;
	}
	
	public Tuple(T1 value1, T2 value2) {
		super();
		this.value1 = value1;
		this.value2 = value2;
	}
	
	@Override
	public String toString() {
		return "Tuple [value1=" + value1 + ", value2=" + value2 + "]";
	}
	@Override
	public int compareTo(Tuple<T1, T2> o) {
		return getValue2().compareTo(o.getValue2());
	}

	
	
}
