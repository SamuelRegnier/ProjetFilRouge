package com.filrouge.ProjetFilRouge.validation;

import java.util.ArrayList;
import java.util.List;

public class BooleanValidation {
	
public static List <String> ErreurBoolean (Boolean bool) {
		
		List <String> listErreurs= new ArrayList <> ();
		
		if (bool != true || bool != false) {
			listErreurs.add("Le champ doit être un boolean; ");
		}
		
		if (bool == null) {
			listErreurs.add("Le champ ne peut pas être null");
		}
		
		return listErreurs;
	}
}
