package com.filrouge.ProjetFilRouge.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class IntegerValidation {
	
	public static List <String> ErreurInt (Integer integer) {
		
		List <String> listErreurs= new ArrayList <> ();
		
			if (integer == null) {
				listErreurs.add("Le nombre ne peut pas être null");
				return listErreurs;
			}
			
			if (integer <= 0) {
				listErreurs.add("Le nombre doit être un entier positif");	
				return listErreurs;
			}
			
			// Vérification si la chaîne ne contient que des chiffres
			String integerStr = Integer.toString(integer);
	        if (!integerStr.matches("\\d+")) {
	        	listErreurs.add("Nombre invalide : contient des caractères non numériques");
	        }

			return listErreurs;
	}
		
}
	


	
