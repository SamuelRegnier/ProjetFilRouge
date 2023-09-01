package com.filrouge.ProjetFilRouge.validation;

import java.util.ArrayList;
import java.util.List;

public class TelNbrValidation {
	
	/*public static void main(String[] args) {
		
		String tel1 = "061525899";
		System.out.println(isTelNbr(tel1));
	
	}*/
	public static List<String> isTelNbr(String telNbr) {
		List<String> erreurs = new ArrayList<>();
		String regExp = "(07|06)(\\d{8})";
		
		if(telNbr == null) {
			erreurs.add("Le numéro de téléphone ne peut pas être null");
		}
		
		if(!telNbr.matches(regExp)) {
			erreurs.add("Mauvais format de numéro de téléphone.");
		}
		return erreurs;
	}
}
