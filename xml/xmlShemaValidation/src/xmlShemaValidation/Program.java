package xmlShemaValidation;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class Program {

	public static void main(String[] args) {
		SchemaFactory sFact = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Source fichier = new StreamSource(new File("repertoire.xml"));
		try {
			Schema schema = sFact.newSchema(new File("repertoire.xsd"));
			Validator validator = schema.newValidator();
			validator.validate(fichier);
			System.out.println("fichier valide");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			System.out.println("fichier invalide" + e);
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
