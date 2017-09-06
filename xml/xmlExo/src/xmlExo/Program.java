package xmlExo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;






public class Program {

	public static void main(String[] args) {
		exportData();
		
	}
	public static void importData() {
		
	}
	public static void exportData() {
		try {
			String nombase = JOptionPane.showInputDialog("nom de la base ? ");
			//String nomtable = JOptionPane.showInputDialog("nom de la tabe ? ");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection base = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ nombase ,"root","");
			
			System.out.println("meta data de la bdd ");
			DatabaseMetaData dbmd = base.getMetaData();
			// args nom base, shema, pattern % equivaut a étoile, type dde donne qui minteresse
			ResultSet rsdbmd = dbmd.getTables(nombase, null, "%", new String[] {"table"});
			boolean exist = false ;
			int count = 0;
			
			OutputStream sortie = new FileOutputStream(new File("sortie.xml"));
			XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(sortie,"utf-8");
			writer.writeStartDocument();
			writer.writeStartElement("data");
			
			while (rsdbmd.next()) {
				exist = true;
				count +=1;
				System.out.println("table existante -> " + rsdbmd.getString("TABLE_NAME"));
				
				writer.writeStartElement("table");
				writer.writeAttribute("name", rsdbmd.getString("TABLE_NAME"));
				

				Statement query = base.createStatement();
				ResultSet rs = query.executeQuery("select * from " +rsdbmd.getString("TABLE_NAME"));
				ResultSetMetaData rsmd = rs.getMetaData();
				writer.writeStartElement("columns");
				for (int i=1; i<=rsmd.getColumnCount();i++) {
					System.out.println(rsmd.getColumnName(i) + " : " +
							rsmd.getColumnTypeName(i) + " : " + rsmd.getColumnType(i));
					
					writer.writeEmptyElement("column");
					writer.writeAttribute("name", rsmd.getColumnName(i));
					writer.writeAttribute("type", rsmd.getColumnTypeName(i));
					
				}
				writer.writeEndElement();
				writer.writeStartElement("rows");
				while(rs.next()) {
					writer.writeStartElement("row");
					for (int i=1; i<=rsmd.getColumnCount();i++) {
						writer.writeStartElement("cell");
						String columName = rsmd.getColumnName(i);
						writer.writeAttribute("name", columName);
						writer.writeCharacters(rs.getString(columName));
						// mm chose que ci dessis mais en moins clair
						/*writer.writeAttribute("name", rsmd.getColumnName(i));
						writer.writeCharacters(rs.getString(i));*/
						//pr cell
						writer.writeEndElement();
					}
					// pr row
					writer.writeEndElement();
				}
				// pr rows
				writer.writeEndElement();
				// pr table
				writer.writeEndElement();
				
				
			}
			//pr data
			writer.writeEndElement();
			writer.writeEndDocument();
			writer.close();
			
			System.out.println("-------------------- debut copie en bdd ---------------------");
			// chemin inverse ouvrir fihcier en lecture recup id puis comparer nb ligne ou id avc bd
			// pour un eventuel insert
			
			XMLReader reader;
			
			//reader = XMLReaderFactory.createXMLReader();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		}
	}
}
