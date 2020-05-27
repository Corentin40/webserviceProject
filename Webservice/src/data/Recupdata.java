package data;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class Recupdata {
	
	 public static void main(String[] args) {
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	      try {
	         factory.setValidating(true);
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         ErrorHandler errHandler = new SimpleErrorHandler();
	         builder.setErrorHandler(errHandler);
	         File fileXML = new File("WebContent\\WEB-INF\\lib\\xmltest.xml");
	         Document xml;
	         try {
	            xml = builder.parse(fileXML);
	            Element root = xml.getDocumentElement();
	            System.out.println(description(root, ""));
	         } catch (SAXParseException e) { }
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }
	   
	   /**
	    * M�thode qui va parser le contenu d'un n�ud
	    * @param n
	    * @param tab
	    * @return
	    */
	   public static String description(Node n, String tab){
	      String str = new String();
	      //Nous nous assurons que le n�ud pass� en param�tre est une instance d'Element
	      //juste au cas o� il s'agisse d'un texte ou d'un espace, etc.
	      if(n instanceof Element){
	         
	         //Nous sommes donc bien sur un �l�ment de notre document
	         //Nous castons l'objet de type Node en type Element
	         Element element = (Element)n;
	         
	         //R�cup�ration du nom du n�ud actuellement parcouru 
	         str += "<" + n.getNodeName();
	         
	         //nous contr�lons la liste des attributs pr�sents
	         if(n.getAttributes() != null && n.getAttributes().getLength() > 0){
	            
	      
	            NamedNodeMap att = n.getAttributes();
	            int nbAtt = att.getLength();
	            
	            //nous parcourons tous les n�uds pour les afficher
	            for(int j = 0; j < nbAtt; j++){
	               Node noeud = att.item(j);
				//On r�cup�re le nom de l'attribut et sa valeur 
	               str += " " + noeud.getNodeName() + "=\"" + noeud.getNodeValue() + "\" ";
	            }
	         }
	         
	         
	         str += ">";
	         
	         //La m�thode getChildNodes retournant le contenu du n�ud + les n�uds enfants
	         //Nous r�cup�rons le contenu texte uniquement lorsqu'il n'y a que du texte, donc un seul enfant
	         if(n.getChildNodes().getLength() == 1)
	              str += n.getTextContent();
	         
	         //Nous allons maintenant traiter les n�uds enfants du n�ud en cours de traitement
	         int nbChild = n.getChildNodes().getLength();
	         //Nous r�cup�rons la liste des n�uds enfants
	         NodeList list = n.getChildNodes();
	         String tab2 = tab + "\t";
	         
	         //nous parcourons la liste des n�uds
	         for(int i = 0; i < nbChild; i++){
	            Node n2 = list.item(i);
	            
	            //si le n�ud enfant est un Element, nous le traitons
	            if (n2 instanceof Element){
	               //appel r�cursif � la m�thode pour le traitement du n�ud et de ses enfants 
	               str += "\n " + tab2 + description(n2, tab2);
	            }
	         }
	         
	         //Nous fermons maintenant la balise
	         if(n.getChildNodes().getLength() < 2)
	            str += "</" + n.getNodeName() + ">";
	         else
	            str += "\n" + tab +"</" + n.getNodeName() + ">";
	      }
	      
	      return str;
	   }   
	

}
