package FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUtils {
	
	public HttpServletResponse VerificationXML(HttpServletRequest source, File baseXML) {
		try {
			boolean foundDate = false;
			boolean foundHeure = false;
			String line;
			String sourceDate = new String("");
			String sourceHeure = new String("");
			File sourceXML = (File) source.getParts();
			FileReader reader = new FileReader(sourceXML);
			BufferedReader br = new BufferedReader(reader);
			String date = "<date>";
			String heure = "<heure>";
			while ((line = br.readLine()) != null) {
				if (line.contains("date")) {
					sourceDate = line.substring(date.length(), date.length()+10);		
				}
				if (line.contains("heure")) {
					sourceHeure = line.substring(heure.length(), heure.length()+8);
				}
			}
			if (sourceDate.length()>0) {
			FileReader baseReader = new FileReader(baseXML);
			BufferedReader baseBr = new BufferedReader(baseReader);
			while((line = baseBr.readLine())!=null) {
				if (line.contains(sourceDate)){
					foundDate= true;
				}
				if (line.contains(sourceHeure)) {
					foundHeure = true;
				}
				if (foundDate== true && foundHeure==true) {
					return true;
				}
				if (line.contains("<client>")) {
					foundHeure = false;
					foundDate = false;
				}
			}
			}
		return false;
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
