package com.sdzee.beans;

public class Utilisateur {

    private String prenom;
    private String nom;
    private String tel;

    public void setPrenom(String prenom) {
	this.prenom = prenom;
    }
    public String getPrenom() {
	return prenom;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }
    public String getNom() {
	return nom;
    }
    
    public void setTel(String tel) {
    	this.tel = tel;
        }
    public String getTel() {
    	return tel;
        }
}