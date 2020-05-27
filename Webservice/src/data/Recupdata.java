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
	    * Méthode qui va parser le contenu d'un nœud
	    * @param n
	    * @param tab
	    * @return
	    */
	   public static String description(Node n, String tab){
	      String str = new String();
	      //Nous nous assurons que le nœud passé en paramètre est une instance d'Element
	      //juste au cas où il s'agisse d'un texte ou d'un espace, etc.
	      if(n instanceof Element){
	         
	         //Nous sommes donc bien sur un élément de notre document
	         //Nous castons l'objet de type Node en type Element
	         Element element = (Element)n;
	         
	         //Récupération du nom du nœud actuellement parcouru 
	         str += "<" + n.getNodeName();
	         
	         //nous contrôlons la liste des attributs présents
	         if(n.getAttributes() != null && n.getAttributes().getLength() > 0){
	            
	      
	            NamedNodeMap att = n.getAttributes();
	            int nbAtt = att.getLength();
	            
	            //nous parcourons tous les nœuds pour les afficher
	            for(int j = 0; j < nbAtt; j++){
	               Node noeud = att.item(j);
				//On récupère le nom de l'attribut et sa valeur 
	               str += " " + noeud.getNodeName() + "=\"" + noeud.getNodeValue() + "\" ";
	            }
	         }
	         
	         
	         str += ">";
	         
	         //La méthode getChildNodes retournant le contenu du nœud + les nœuds enfants
	         //Nous récupérons le contenu texte uniquement lorsqu'il n'y a que du texte, donc un seul enfant
	         if(n.getChildNodes().getLength() == 1)
	              str += n.getTextContent();
	         
	         //Nous allons maintenant traiter les nœuds enfants du nœud en cours de traitement
	         int nbChild = n.getChildNodes().getLength();
	         //Nous récupérons la liste des nœuds enfants
	         NodeList list = n.getChildNodes();
	         String tab2 = tab + "\t";
	         
	         //nous parcourons la liste des nœuds
	         for(int i = 0; i < nbChild; i++){
	            Node n2 = list.item(i);
	            
	            //si le nœud enfant est un Element, nous le traitons
	            if (n2 instanceof Element){
	               //appel récursif à la méthode pour le traitement du nœud et de ses enfants 
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
