package com.filrouge.ProjetFilRouge.validation;

import java.util.ArrayList;
import java.util.List;

public class StringValidation {
	public static final int MIN_LONGUEUR_NOM = 3;
	public static final int MAX_LONGUEUR_NOM = 15;
	
	public static List<String> erreurNom(String nom){
		List<String> erreurs = new ArrayList<>();
		if (nom == null) {
			erreurs.add("Le nom ne peut pas être null.");
			return erreurs;
		}
		if (!nom.matches("^[a-zA-Z]*$")) {
			erreurs.add("Le nom ne doit comporter que des lettres.");
		}
		if (nom.length()<3 || nom.length()>15) {
			erreurs.add("Le nom doit comporter minimum 3 charactères et maximum 15 charactères");
		}
		return erreurs;
	}
	
	public static List<String> erreurPrenom(String prenom){
		List<String> erreurs = new ArrayList<>();
		if (prenom == null) {
			erreurs.add("Le prénom ne peut pas être null.");
			return erreurs;
		}
		if (!prenom.matches("^[a-zA-Z]*$")) {
			erreurs.add("Le prénom ne doit comporter que des lettres.");
		}
		if (prenom.length()<3 || prenom.length()>15) {
			erreurs.add("Le prénom doit comporter minimum 3 charactères et maximum 15 charactères");
		}
		return erreurs;
	}
	
	public static List<String> erreurAdresse(String adresse){
		List<String> erreurs = new ArrayList<>();
		if (adresse == null) {
			erreurs.add("L'adresse ne peut pas être null.");
			return erreurs;
		}
		/*if (!prenom.matches("^[a-zA-Z]*$")) {
			erreurs.add("L'adresse ne doit comporter que des lettres.");
		}*/
		if (adresse.length()<3 || adresse.length()>30) {
			erreurs.add("L'adresse doit comporter minimum 3 charactères et maximum 30 charactères");
		}
		return erreurs;
	}
	
	public static List<String> erreurStatut(String statut){
		List<String> erreurs = new ArrayList<>();
		if (statut == null) {
			erreurs.add("Le statut ne peut pas être null.");
			return erreurs;
		}
		if (!statut.matches("^[a-zA-Z]*$")) {
			erreurs.add("Le statut ne doit comporter que des lettres.");
		}
		if (statut.length()<3 || statut.length()>15) {
			erreurs.add("Le statut doit comporter minimum 3 charactères et maximum 15 charactères");
		}
		return erreurs;
	}

}
