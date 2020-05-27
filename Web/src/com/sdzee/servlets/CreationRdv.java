package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Utilisateur;
import com.sdzee.forms.FormulaireForm;

public class CreationRdv extends HttpServlet {
	public static final String VUE_FORM = "/WEB-INF/formulaire.jsp";
	public static final String VUE_SUCCES = "/WEB-INF/afficherFormulaire.jsp";
	
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";


	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		/* Affichage de la page de rendez-vous */
		this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
	}
	/* R�cup�ration des champs du formulaire. */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Pr�paration de l'objet formulaire */
		FormulaireForm form = new FormulaireForm();
		
		/* Appel au traitement et � la validation de la requ�te, et r�cup�ration du bean en r�sultant */
        Utilisateur utilisateur = form.creerRdv( request );
        
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );
        
        
        
        if ( form.getErreurs().isEmpty() ) {
            /* Si aucune erreur, alors affichage de la fiche r�capitulative */
            this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward( request, response );
            System.out.println("Succes");
        } else {
            /* Sinon, r�-affichage du formulaire de cr�ation avec les erreurs */
            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
         
        }
    	
	} 
	
	
}
	
	




