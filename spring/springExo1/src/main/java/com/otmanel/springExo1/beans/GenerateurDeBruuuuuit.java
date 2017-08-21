package com.otmanel.springExo1.beans;

import java.util.Random;

public class GenerateurDeBruuuuuit {
	public void applaudir(IArtitste artiste) {
		System.out.println("clap clap clap clap clap !");
	}
	public void huer(IArtitste artiste) {
		Random r = new Random();
		if(r.nextBoolean())
			System.out.println("bouuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuh \n"
					+ " degage tu pue !");
		else System.out.println(" bravo -clap clap clap clap clap !");
	}
}
