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
		if (!nom.matches("[a-zA-Z][a-zA-Z](-){2}[A-Za-z]")) {
			erreurs.add("Le nom ne doit comporter que des lettres.");
		}
		if (nom.length()<3 || nom.length()>15) {
			erreurs.add("Le nom doit comporter minimum 3 charactères et maximum 15 charactères");
		}
		return erreurs;
	}

}
