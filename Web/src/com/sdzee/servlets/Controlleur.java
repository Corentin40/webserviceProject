package com.sdzee.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Controlleur extends HttpServlet {
	public static final String VUE = "/WEB-INF/Formulaire.jsp";
	public static final String CHAMP_PRENOM = "prenom";
	public static final String CHAMP_NOM = "nom";
	public static final String CHAMP_TEL = "tel";
	public static final String ATT_ERREURS  = "erreurs";
	public static final String ATT_RESULTAT = "resultat";
	private String resultat;



	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		/* Affichage de la page d'inscription */
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}
	/* R�cup�ration des champs du formulaire. */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		
		String resultat;
		Map<String, String> erreurs = new HashMap<String, String>();
		
		//R�cup�ration des param�tres envoy�s via la m�thode POST
		String prenom = request.getParameter( CHAMP_PRENOM ); 
		String nom = request.getParameter( CHAMP_NOM );
		String tel = request.getParameter( CHAMP_TEL );
		
		
		//On sauvegarde les donn�es dans une session
		session.setAttribute("nom", nom);
		session.setAttribute("prenom", prenom);
		session.setAttribute("telephone", tel);
		
		
		
		/* G�rer les erreurs de validation ici. */
		try {
			validationPrenom( prenom );}
		catch(Exception e) {
			erreurs.put(CHAMP_PRENOM, e.getMessage());
		}

		try {
			validationNom( nom );}
		catch (Exception e) {
			erreurs.put(CHAMP_NOM, e.getMessage());

			try {
				validationTel( tel );}
			catch(Exception e1) {
				erreurs.put(CHAMP_TEL, e1.getMessage());
			}


			/* Initialisation du r�sultat global de la validation. */
			if ( erreurs.isEmpty() ) {
				resultat = "Rendez-vous enregistr�";
			} else {
				resultat = "�chec de l'enregistrement du rendez-vous";
			}
			

			/* Stockage du r�sultat et des messages d'erreur dans l'objet request */
			request.setAttribute( ATT_ERREURS, erreurs );
			request.setAttribute( ATT_RESULTAT, resultat );

			
			/* Transmission de la paire d'objets request/response � notre JSP */
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		}
	} 
	
	
	
	private void validationPrenom( String prenom ) throws Exception{
		if (prenom.trim().length() != 0) {
			
			throw new Exception ( "Le nom doit etre renseign�.");
				
			}
		}
	
	private void validationNom( String nom ) throws Exception{
		if (nom.trim().length()<0) {
			throw new Exception ( "Le prenom doit �tre renseign�.");
		
	}
		
		}
	
	private void validationTel( String tel ) throws Exception{
		if (tel.trim().length()<0) {
			throw new Exception ( "Le telephone doit �tre renseign�.");
	}
		else if (tel.trim().length()<10) {
			throw new Exception("Le num�ro de t�l�phone est incomplet.");
		}
		
	}
}



