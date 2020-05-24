package FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUtils {
	/* 
	 * Méthode permettant de savoir si le rdv est déjà pris à cette horaire
	 * */
	public HttpServletResponse VerificationBaseXML(HttpServletRequest source, File baseXML) {
		try {
			HttpServletResponse result = null;
			boolean foundDate = false;
			String rdvDate;
			String line;
			String BaliseDate = new String("<date>");
			String BaliseHeure = new String("<heure>");
			String sourceDate = new String("");
			String sourceHeure = new String("");
			File sourceXML = (File) source.getParts();
			FileReader reader = new FileReader(sourceXML);
			BufferedReader br = new BufferedReader(reader);
			while ((line = br.readLine()) != null) {
				if (line.contains("date")) { // à vérifier car je ne sais pas comment on reçoit exactement l'information
					sourceDate = line.substring(BaliseDate.length(), BaliseDate.length()+10);		
				}
				if (line.contains("heure")) { // pareil
					sourceHeure = line.substring(BaliseHeure.length(), BaliseHeure.length()+8);
				}
			}
			rdvDate = sourceDate + " " + sourceHeure;
			if (rdvDate.length()>18) { // il faudra recompter
			FileReader baseReader = new FileReader(baseXML);
			BufferedReader baseBr = new BufferedReader(baseReader);
			while((line = baseBr.readLine())!=null) {
				if (line.contains(rdvDate)){
					foundDate= true;
				}
				if (foundDate== true) {
					result.addHeader("Réponse", "SC_FORBIDDEN"); // à vérifier
					return result;
				}
			}
		}
		result.addHeader("Réponse", "SC_ACCEPTED"); // à vérifier
		return result;
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	/*
	 * Méthode pour la secrétaire afin de savoir à quelle heure à rdv qqun
	 * */
	public String getRDV (String nom) throws IOException {
		String result="";
		String line;
		boolean foundClient=false;
		File baseXML;
		FileReader baseReader = new FileReader(baseXML);
		BufferedReader baseBr = new BufferedReader(baseReader);
		while((line = baseBr.readLine())!=null) {
			if(line.contains(nom)) {
				foundClient = true;
			}
			if(foundClient == true && line.contains("rdv")) {
				result = line.substring(5, 23); // à recompter
			}
		}
		if (result == "") {
			return "Désolé aucun rendez-vous trouvé !";
		}
		return result;
	}
	// Méthode pour savoir qui à rdv à une heure donnée
	public String getNom (String heure) throws IOException {
		String result="";
		String line="";
		boolean foundClient=false;
		boolean found = false;
		File baseXML;
		int compteur=0;
		FileReader baseReader = new FileReader(baseXML);
		BufferedReader baseBr = new BufferedReader(baseReader);
		while((line = baseBr.readLine())!=null) {
			compteur++;
			if (line.contains(heure)) {
				found = true;
				baseBr.mark(compteur -4);
				baseBr.reset();
			}
			if (found == true && (line.contains("prenom"))){
				result = result + line.substring(8, (line.length()-8));// à recompter
			}
			if (found==true && line.contains("nom")) {
				result = result + line.substring(5, line.length()-5);// à recompter
			}
		}
		return result;
	}
	// méthode donnant tous les rdv d'une journée
	public String getToday(String date) throws IOException {
		File baseXML;
		boolean found = false;
		String result="";
		String line;
		int compteur=0;
		FileReader baseReader = new FileReader(baseXML);
		BufferedReader baseBr = new BufferedReader(baseReader);
		while((line = baseBr.readLine())!=null) {
			compteur++;
			if (line.contains(date)) {
				found = true;
				baseBr.mark(compteur -4);
				baseBr.reset();
			}
			if (found == true && (line.contains("prenom"))){
				result = result + "Prénom : " + line.substring(8, (line.length()-8));// à recompter
			}
			if (found==true && line.contains("nom")) {
				result = result + " Nom : " + line.substring(5, line.length()-5);//à recompter
			}
			if (found==true && line.contains("numéro")) {
				result = result + " Numéro : " + line.substring(5, line.length()-5);// à recompter
			}
			if (found==true && line.contains("rdv")) {
				result = result + " rdv : " + line.substring(5, line.length()-5) + " ##### "; // à recompter
			}
			
		}
		return result;
	}
}
