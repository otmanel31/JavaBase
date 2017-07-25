package designPatterMoyen;

public class Program {

	public static void main(String[] args) {
		// ici le computer builder est un builder renvoyant une instance de computer
		// cependant un vbuilder n est pas forcement une classe interne ou indiredctement lié a 
		//  - lobjet construit
		// design pattern buidler
		/*Computer c1 = new Computer.Builder("core 2 intel", 16)
				.build();
		
		System.out.println(c1);
		String str = new StringBuilder("hello ")
				.append("world")
				.append("\n")
				.reverse()
				.insert(3, "#")
				.toString(); // mm chose que ce qon a fait ns meme
		System.out.println(str);*/
		Deplacement d = new Deplacement();
		// jutilise le factory pour me fournir un deplcament
		DeplacementFactory df = d.builDeplacement("paris", "dakkar");
		System.out.println(df);
		System.out.println("duree " + df.getDuree());
		
		
	}

}
