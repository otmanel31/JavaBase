package designPattern;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
 * Il ne doit y en avoir q'une (instance)
 */

public class CustomConfig {
	/*
	 * 1- On va en premier lieu limité l'acces au constructeur pour test singleton
	 * 2- on va memoriser dans un attribut de classe l'instance un ique de lobjet a renvoyé
	 * 
	 */
	
	private static CustomConfig theInstance = null;
	
	private LocalDateTime dateCreated;
	private Map<String, String> configValue;
	
	private CustomConfig() {
		this.dateCreated = LocalDateTime.now();
		this.configValue = new HashMap<>();
	}
	
	public void addValueTodConfig(String cle, String valeur) {
		this.configValue.put(cle, valeur);
	}
	
	public String getValueFronConfig(String cle) {
		return this.configValue.get(cle);
	}
	
	public void saveToPropertiesFile(String fileName) {
		Properties prop = new Properties();
		OutputStream output = null;
		try {
			output = new FileOutputStream(fileName);
			this.configValue.keySet().stream()
				.forEach(cle-> prop.setProperty(cle, this.configValue.get(cle)));
			prop.store(output, "generated by custom config");
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadFromPropertiesFile(String fileName) {
		this.configValue.clear();
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream(fileName);
			prop.load(input);
			prop.keySet().stream().forEach(
					cle -> this.configValue.put(cle.toString(), prop.getProperty(cle.toString()))
					);
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static CustomConfig getInstance() {
		// si instance nexiste pas on la creer
		// et on la memeorise pour la renvoyer a la suite
		// il n y  aura qune instance qui sera apellé et c celle la : SINGLETON
		if (theInstance == null) {
			theInstance = new CustomConfig();
		}
		return theInstance;
	}

	@Override
	public String toString() {
		return "CustomConfig [dateCreated=" + dateCreated + "]";
	}
	// ce design pattern est tres pratique mais ne pas en abuser
	// cela peux devenir problematique pour 2 raison
	/*
	 *  1- un etat partagé sur tte lappli, plein deffet de bord potentiel
	 *  	ex une fonction modife une valeur non vouleur par une autre fonction ... 
	 *  2- problematique en cas de programation multi thread
	 */
	
}
