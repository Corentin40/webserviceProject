package com.sdzee.forms;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.beans.Utilisateur;


public final class FormulaireForm {
	public static final String CHAMP_PRENOM = "prenom";
	public static final String CHAMP_NOM = "nom";
	public static final String CHAMP_TEL = "tel";
	public static final String ATT_ERREURS  = "erreurs";
	public static final String ATT_RESULTAT = "resultat";

	String resultat;
	Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return resultat;

	}

	public Map<String,String> getErreurs(){
		return erreurs;

	}

	public Utilisateur creerRdv(HttpServletRequest request) {
		String prenom = getValeurChamp( request, CHAMP_PRENOM );
		String nom = getValeurChamp( request, CHAMP_NOM );
		String tel = getValeurChamp( request, CHAMP_TEL );

		Utilisateur utilisateur = new Utilisateur();

		try {
			validationPrenom( prenom );
		} catch ( Exception e ) {
			setErreur( CHAMP_PRENOM, e.getMessage() );
		}
		utilisateur.setPrenom( prenom );

		try {
			validationNom( nom );
		} catch ( Exception e ) {
			setErreur( CHAMP_NOM, e.getMessage() );
		}
		utilisateur.setNom( nom );

		try {
			validationTel( tel );
		} catch ( Exception e ) {
			setErreur( CHAMP_TEL, e.getMessage() );
		}
		utilisateur.setTel( tel );

		if ( erreurs.isEmpty() ) {
			resultat = "Enregistrement reussi.";
		} else {
			resultat = "Échec de l'enregistrement.";
		}

		return utilisateur;
	}

	private void validationPrenom( String prenom ) throws Exception{
		if (prenom != null) {

			throw new Exception ( "Veuillez renseigner ce champ.");

		}
	}
		
		
	private void validationNom( String nom ) throws Exception{
		if (nom != null) {
			
			throw new Exception ( "Veuillez renseigner ce champ.");

			}

		}
	
	private void validationTel( String tel ) throws Exception{
		if (!tel.matches("^\\d+$" )) {
			throw new Exception ("Le numéro de téléphone doit uniquement contenir des chiffres");
		}
		
		else if (tel.length()<10) {
			throw new Exception ( "Le champ téléphone doit contenir 10 caractères.");
		}

	}

	
	
	/*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);

	}

	
	

	private String getValeurChamp(HttpServletRequest request, String nomChamp) {

		String valeur = request.getParameter( nomChamp );
		if ( valeur == null || valeur.trim().length() == 0 ) {
			return null;
		} else {
			return valeur;
		}
	}

}
