package com.filrouge.ProjetFilRouge.validation;

public class TelNbrValidation {
	
	public static void main(String[] args) {
		
		String tel1 = "061525899";
		System.out.println(isTelNbr(tel1));
	
	}
	public static String isTelNbr(String telNbr) {
		String regExp = "(07|06)(\\d{8})";
		
		if(telNbr.matches(regExp)) {
			return telNbr;
		}else {
			return "Mauvais format de numéro de téléphone.";
		}
	}
}
