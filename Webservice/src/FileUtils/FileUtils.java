package FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUtils {
	
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
				if (line.contains("date")) {
					sourceDate = line.substring(BaliseDate.length(), BaliseDate.length()+10);		
				}
				if (line.contains("heure")) {
					sourceHeure = line.substring(BaliseHeure.length(), BaliseHeure.length()+8);
				}
			}
			rdvDate = sourceDate + " " + sourceHeure;
			if (rdvDate.length()>18) {
			FileReader baseReader = new FileReader(baseXML);
			BufferedReader baseBr = new BufferedReader(baseReader);
			while((line = baseBr.readLine())!=null) {
				if (line.contains(rdvDate)){
					foundDate= true;
				}
				if (foundDate== true) {
					result.addHeader("Réponse", "SC_FORBIDDEN");
					return result;
				}
			}
		}
		result.addHeader("Réponse", "SC_ACCEPTED");
		return result;
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	
}
