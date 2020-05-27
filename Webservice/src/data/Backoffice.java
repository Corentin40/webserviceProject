package data;


	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.IOException;

	import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.ParserConfigurationException;
	import javax.xml.transform.Transformer;
	import javax.xml.transform.TransformerConfigurationException;
	import javax.xml.transform.TransformerException;
	import javax.xml.transform.TransformerFactory;
	import javax.xml.transform.TransformerFactoryConfigurationError;
	import javax.xml.transform.dom.DOMSource;
	import javax.xml.transform.stream.StreamResult;
	import javax.xml.transform.stream.StreamSource;

	import org.w3c.dom.DOMException;
	import org.w3c.dom.Document;
	import org.xml.sax.SAXException;

	public class Backoffice {

	   public static void main(String[] args) {
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      factory.setIgnoringElementContentWhitespace(true);
	      try {
	         //lecture de notre document XML
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         File fileXML = new File("WebContent\\WEB-INF\\lib\\xmltest.xml");
	         Document xml = builder.parse(fileXML);
	         
	         //définition de nos deux fichiers XSL
	         File tableStyleSheet = new File("WebContent\\WEB-INF\\lib\\table.xsl");
	         File listStyleSheet = new File("WebContent\\WEB-INF\\lib\\list.xsl");
	         
	         //Création de nos objets pour la transformation
	         StreamSource styleSource = new StreamSource(tableStyleSheet);
	         Transformer t = TransformerFactory.newInstance().newTransformer(styleSource);
	         //Pour la transformation en tableau, j'ai mis trois paramètres
	         t.setParameter("bgColor", "black");
	         t.setParameter("fgColor", "white");
	         t.setParameter("contentBgColor", "silver");
	         
	         //Le fichier où nous allons enregistrer le résultat
	         String resultFile = "table.html"; 
	         StreamResult HTML = new StreamResult(resultFile);
	         //transformation et enregistrement dans le fichier
	         t.transform(new DOMSource(xml), HTML);
	         
	         //On refait le tout, mais cette fois avec la deuxième feuille XSL
	         styleSource = new StreamSource(listStyleSheet);
	         t = TransformerFactory.newInstance().newTransformer(styleSource);
	         
	         String resultFile2 = "list.html"; 
	         StreamResult HTML2 = new StreamResult(resultFile2);
	         //transformation et enregistrement dans le fichier
	         t.transform(new DOMSource(xml), HTML2);
	         
	         //On affiche le résultat de la transformation dans notre Fenetre
	         Fenetre fen2 = new Fenetre(resultFile, resultFile2);
	         
	      } catch (DOMException e) {
	         e.printStackTrace();
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (TransformerConfigurationException e) {
	         e.printStackTrace();
	      } catch (TransformerFactoryConfigurationError e) {
	         e.printStackTrace();
	      } catch (TransformerException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }       
	   }
	

}
