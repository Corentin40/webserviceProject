package data;

public class Client {
	
	private String NomClient; 
	private String PrenomClient; 
	private String NumeroClient; 
	private String emailClient; 
	private String Date; 
	private String Heure; 
	
	
	// Contructeur de client
	public Client(String NomClient, String PrenomClient, String NumeroClient, String emailClient, String Date, String Heure) {
		
	this.setNomClient(NomClient); 
	this.setPrenomClient(PrenomClient); 
	this.setNumeroClient(NumeroClient); 
	this.setEmailClient(emailClient); 
	this.setDate(Date); 
	this.setHeure(Heure);
	}

//getter et setter pour les params (encapsulation)
	public String getNomClient() {
		return NomClient;
	}

	public void setNomClient(String nomClient) {
		NomClient = nomClient;
	}

	public String getPrenomClient() {
		return PrenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		PrenomClient = prenomClient;
	}

	public String getNumeroClient() {
		return NumeroClient;
	}

	public void setNumeroClient(String numeroClient) {
		NumeroClient = numeroClient;
	}

	public String getEmailClient() {
		return emailClient;
	}

	public void setEmailClient(String emailClient) {
		this.emailClient = emailClient;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getHeure() {
		return Heure;
	}

	public void setHeure(String heure) {
		Heure = heure;
	}

	

}
