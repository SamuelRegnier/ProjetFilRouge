package com.filrouge.ProjetFilRouge.exception;

import java.util.Arrays;
import java.util.List;

public class StringException extends RuntimeException {
	
	public StringException(String message) {
		super(message);
	}
	
	public  StringException (String message , Throwable cause) {
		super (message, cause); 
	}
	
	public  StringException (Throwable cause) {
		super (cause); 
	}
	
	public StringException(List<String> messages) {
		//To do convertir messages en string
		this(transformerList(messages));
	}
	
	public static String transformerList(List<String> messages) {
		String delim = "\n";
		StringBuilder sb = new StringBuilder();
		int i=0;
		while(i<messages.size()-1) {
			sb.append(messages.get(i));
			sb.append(delim);
			i++;
		}
		sb.append(messages.get(i));
		String message = sb.toString();
		return message;
	}

}
