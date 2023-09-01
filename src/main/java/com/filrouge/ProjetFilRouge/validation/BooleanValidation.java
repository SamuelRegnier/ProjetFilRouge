package com.filrouge.ProjetFilRouge.validation;

import java.util.ArrayList;
import java.util.List;

public class BooleanValidation {
	
public static List <String> ErreurBoolean (Boolean bool) {
		
		List <String> listErreurs= new ArrayList <> ();
		
		if (bool != true || bool != false) {
			listErreurs.add("L'entrée doit être un boolean; ");
			return listErreurs;
		}
		
		return listErreurs;
	}
}
