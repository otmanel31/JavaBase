package xmlSax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class MyContentHandler implements ContentHandler {

	@Override
	public void startDocument() throws SAXException {
		// appeler quand on demarre le parsing du document
		System.out.println("debur du document");

	}

	@Override
	public void endDocument() throws SAXException {
		// appeler quand on termine le parsing du document
		System.out.println("debur du document");
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		// appeler quand on rencontre une balise ouvrante
		System.out.println("nouvelle balise ouvrante :-> " + localName);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// quand on rencontre une balise fermante
		System.out.println("nouvelle balise fermmante :-> " + localName);
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// appeler quand on rencontre du texte 
		// pour des question de performance il utilise un tableau de caractere 
		// donc pour ne pas creer une nouvelle allocation memoire il nous renvpioe
		// le point de depart de la chaone de caracrere et de fin dabs le tableau existabt
		// ex : ['<',  't','e','x','t'.....'</>] non creation d1 espace memoire pour 'text'
		String text = new String(ch, start, length);
		System.out.println("texte -(> " + text);

	}
	
	
	
	// ci dessous pas besoin ici
	
	@Override
	public void setDocumentLocator(Locator locator) {
		

	}

	

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		

	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		

	}

	

	

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		

	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		

	}

}
