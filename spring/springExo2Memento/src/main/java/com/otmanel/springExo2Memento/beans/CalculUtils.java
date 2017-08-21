package com.otmanel.springExo2Memento.beans;

public class CalculUtils {
	public int fonctionCarre(int i) {
		return i*i;
	}
	public int fibonacci(int n) {
		if (n <= 1) return n;
        else return fibonacci(n-1) + fibonacci(n-2);
	}
}
