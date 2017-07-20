package premiereBase;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

public class Program {
 // BASE REQUETES JDBC
	public static void main(String[] args) {
		// charger le driver mySQL
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			// on peut maintenant demander un connexion au driver manager car celui
			// ci gere maintenant aussi les connexion mysql
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/firstbase",
					"root","");
			System.out.println("connexion establisheed");
			// je veux lister tt les clients
			// pour cela utiliosation dun statement ==> objet encapsulant la requete 
			// erxecuté
			Statement stat = connection.createStatement();
			ResultSet result = stat.executeQuery("SELECT * FROM Client"); // renvoie un result set
			// le resulset renvoyé permet de parcourir les lignes renvoyéées
			// ns somme en mide connecté
			// results est un curseur sur le resultat de lexecution
			// il va recuperer au fur et a mesure du parcours des resulats les lignes
			// suivantes depuis la base
			
			// next passe a la ligne suivante et ns renvoie true tt quil reste une ligne a lire
			while(result.next()){
				System.out.println("id =" + result.getInt("id") + 
								" nom="+ result.getString("nom")+
								" prenom=" + result.getString("prenom") + 
								" email=" + result.getString("email") +
								" solde=" + result.getDouble("solde"));	
			}
			// ne pas oublier de fermer le flux et liberation des ressources
			result.close();
			
			Scanner reader = new Scanner(System.in);
			System.out.println("Nom nouveau client - nom>:");
			String nom = reader.nextLine();
			System.out.println("Nom nouveau client - prenom>:");
			String prenom = reader.nextLine();
			System.out.println("Nom nouveau client - eamail>:");
			String email = reader.nextLine();
			System.out.println("Nom nouveau client - solde>:");
			double solde = Double.parseDouble(reader.nextLine());
			
			// requete paramétré ou préparé
			// CallAbleStatement ==> appel de procedure stocké
			java.sql.PreparedStatement insertSta = connection.prepareStatement(
					"INSERT INTO `client` (`nom`, `prenom`, `email`, `solde`)" +
					" VALUES (?,?,?,?)");
			// index parametre est ands lordre des ? dans la requete
			// Attention commence a 1 et pas 0
			insertSta.setString(1, nom);
			insertSta.setString(2, prenom);
			insertSta.setString(3, email);
			insertSta.setDouble(4, solde);

			int nbLignes = insertSta.executeUpdate();
			System.out.println("nb lignes " + nbLignes);
			
			/* ANCIEN A EVITER MAIS CONAITRE 
			Statement inserSta = connection.createStatement();
			// attention C MAL pour rason INJECTION sql et performance
			// INJECTION sql o@o.fr, 10000000) --
			String insertSql = "INSERT INTO `client` (`nom`, `prenom`, `email`, `solde`)" +
					" VALUES ('"+ nom +"','"+ prenom +"', '"+email  +"', "+solde+")";
			// executeUpdate ==> execute request update
			System.out.println(insertSql);

			int nbLigne = inserSta.executeUpdate(insertSql);
			System.out.println("nb lignes inserer " + nbLigne);
			*/
			
			System.out.println("Quelle augmentation de solde ?");
			double augmentation = Double.parseDouble(reader.nextLine());
			System.out.println("concerne les soldes au desus d combien ? ");
			double seuil = Double.parseDouble(reader.nextLine());
			java.sql.PreparedStatement updateSta = connection.prepareStatement(
					"UPDATE `client` set `solde`=`solde` + ? WHERE `solde`>?");
			
			updateSta.setDouble(1, augmentation);
			updateSta.setDouble(2, seuil);
			System.out.println(updateSta.executeUpdate() + " lignes modifiés !");
			
			java.sql.PreparedStatement deleteSta = connection.prepareStatement(
					"DELETE FROM `client` WHERE `id`= ?");
			System.out.println("ID DU CLIENT A EFFECER ? ");
			int cid = Integer.parseInt(reader.nextLine());
			deleteSta.setInt(1, cid);
			System.out.println(deleteSta.executeUpdate() + " nb ligne deleter !");
			
			java.sql.PreparedStatement countSta = connection.prepareStatement(
					"SELECT COUNT(id) from `client` WHERE `solde` < ? ");
			System.out.println("solde max a compter ?");
			double max = Double.parseDouble(reader.nextLine());
			countSta.setDouble(1, max);
			ResultSet rs = countSta.executeQuery();
			if(rs.next()) {
				System.out.println("n b client ss le seuil " + rs.getInt(1));
			}
			rs.close();
			
			
			connection.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
