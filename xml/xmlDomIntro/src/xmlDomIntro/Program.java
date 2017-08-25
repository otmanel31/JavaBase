package xmlDomIntro;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Program {

	public static void main(String[] args) {
		// utilisation de l api dom
		
		// dbf permet de configurer le parsing de document comme par exemple 
		// lutilisationd e shema ou de namespace
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			// le db lui est lobjet concret qui permet de construire un document
			// - soit a  partir dun fhichier xml
			// - soit a partir dun document vierge
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document doc1 = db.parse(new File("repertoire.xml"));
			// un node ?  classe de base de ts ce quon peux rencontrer dans un doc dom
			// ex : des elements ... attributs ..
			NodeList tags = doc1.getElementsByTagName("nom");
			for (int i=0; i< tags.getLength(); i++) {
				Element balise = (Element)tags.item(i);
				System.out.println("tagname " + balise.getTagName());
				System.out.println("contenu " + balise.getTextContent());
			}
			System.out.println(" ----------------------------------------------------------- ");
			// obtenir la racine du document
			NodeList nodes =  doc1.getChildNodes();
			explore(nodes);
			System.out.println(" AJOUT DATAS DS XML EXISTANT ---------------  ");
			nodes = doc1.getElementsByTagName("entree");
			Random rd = new Random();
			for (int i=0; i<nodes.getLength(); i++) {
				Element entree = (Element)nodes.item(i);
				// pour ajouter quelque chose a un document commme une baslie il ne faut
				// pas instancier ns mm cette balise mais demander au document DOM de ns la creer
				Element compte = doc1.createElement("compte");
				// puis je lajoute comme enfant de ma balise entree
				entree.appendChild(compte);
				compte.setAttribute("noCompte", "CP"+rd.nextInt(5000));
				Element solde = doc1.createElement("solde");
				compte.appendChild(solde);
				Text texte = doc1.createTextNode(""+rd.nextDouble()*1000);
				solde.appendChild(texte);
			}
			TransformerFactory tfact = TransformerFactory.newInstance();
			// objet responsable d ela transformation du dom en fichier xml
			// mais en fait il pex transformer nimporte kel source xml en une sortie XML
			Transformer tf = tfact.newTransformer();
			// ma source est le document doc1
			DOMSource source = new DOMSource(doc1);
			// destination stremsresult
			StreamResult destination = new StreamResult(new File("repertoire2.xml"));
			// je veux un fichier inventee
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			// ecriture 
			tf.transform(source, destination);
			System.out.println(" NEW XML DOC ---------------  ");
			Document doc2 = db.newDocument();
			Element racine = doc2.createElement("racine");
			doc2.appendChild(racine);
			for (int i=0; i<10; i++) {
				Element e1 = doc2.createElement("item");
				Text t= doc2.createTextNode("texte" + i);
				e1.appendChild(t);
				racine.appendChild(e1);
			}
			source = new DOMSource(doc2);
			destination = new StreamResult(new File("export.xml"));
			tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			tf.transform(source, destination);
			
			// Utilisation de xpathFactory
			XPathFactory xpf = XPathFactory.newInstance();
			XPath xp = xpf.newXPath();
			// le double // signifie a la racie du document puis balise entree dans repertoire
			XPathExpression xpe  = xp.compile("//repertoire/entree/adresse/rue/text()");
			System.out.println(" xpathhhhh      --------------------------------------------  ");
			NodeList result  = (NodeList)xpe.evaluate(doc1, XPathConstants.NODESET);
			for (int i=0; i< result.getLength(); i++) {
				System.out.println("result xpath " + result.item(i).getNodeValue());
			}
			System.out.println(" xpathhhhh      --------------------------------------------  ");
			// @ sorte de close where
			xpe  = xp.compile("//repertoire/entree/adresse[@codePays=2]/ville/text()");

			result  = (NodeList)xpe.evaluate(doc1, XPathConstants.NODESET);
			for (int i=0; i< result.getLength(); i++) {
				System.out.println("result xpath " + result.item(i).getNodeValue());
			}
			
			System.out.println(" xpathhhhh      --------------------------------------------  ");
			// ici clause where puis retour en arriere et recuperer les noms
			xpe  = xp.compile("//repertoire/entree/adresse[@codePays=2]/../nom/text()");
			result  = (NodeList)xpe.evaluate(doc1, XPathConstants.NODESET);
			for (int i=0; i< result.getLength(); i++) {
				System.out.println("result xpath " + result.item(i).getNodeValue());
			}
					
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void  explore(NodeList nodes) {
		for(int i=0; i<nodes.getLength(); i++) {
			Node n = nodes.item(i);
			switch(n.getNodeType()) {
				case Node.COMMENT_NODE:
					System.out.println("commentaire"); break;
				case Node.TEXT_NODE:
					System.out.println("text" + n.getTextContent()); break;
				case Node.ELEMENT_NODE:
					System.out.println("balise" + ((Element) n).getTagName()); 
					System.out.println(">>>>>>>>>>>>>>>");
					explore(n.getChildNodes());
					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<");
					break;
				case Node.ATTRIBUTE_NODE:
					System.out.println("attiubut " + n.getNodeName()); break;
			}
		}
	}

}
