package com.filrouge.ProjetFilRouge.validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Training;
import com.filrouge.ProjetFilRouge.service.TrainingService;

public class DateValidation {
	
	/*TESTS : 
	 * public static void main(String[] args) {
		Date today = new Date();
		long eighteenYearsBefore = 18L * 365 * 24 * 60 * 60 * 1000;
		System.out.println(isDateNaissance(new Date(today.getTime() - eighteenYearsBefore)));
	}*/
	public static List<String> isDateDebut(Object date, TrainingService trainingService, Training training) {
		List<String> erreurs = new ArrayList<String>();
		if (date == null) {
			erreurs.add("Date is null");
			return erreurs;
		}
		if (!(date instanceof Date)) {
			erreurs.add("Date is not a date");
			return erreurs;
		}
		Date date2 = (Date) date;
		Date today = new Date();
		if (date2.before(today)) {
			erreurs.add("Date is before today");
		}
		if (trainingService.newSessionTooEarly(training, (Date) date)) {
			erreurs.add("Date is invalid for a new session");
		}
		return erreurs;
	}
	
	public static List<String> isDateFin(Date dateDebut, Object dateFin) {
		List<String> erreurs = new ArrayList<String>();
		if (dateFin == null) {
			erreurs.add("Date is null");
			return erreurs;
		}
		if (!(dateFin instanceof Date)) {
			erreurs.add("Date is not a date");
			return erreurs;
		}
		Date date2 = (Date) dateFin;
		Date today = new Date();
		if (date2.before(today)) {
			erreurs.add("Date is before today");
		}
		if (date2.before(dateDebut)) {
			erreurs.add("End date is before start date");
		}
		return erreurs;
	}

	public static List<String> isDateNaissance(Date date) {
		List<String> erreurs = new ArrayList<String>();
		if (date == null) {
			erreurs.add("Date is null");
			return erreurs;
		}
		if (!(date instanceof Date)) {
			erreurs.add("Date is not a date");
			return erreurs;
		}
		Date date2 = (Date) date;
		Date today = new Date();
		long eighteenYearsBefore = 18L * 365 * 24 * 60 * 60 * 1000;
		Date eighteen = new Date();
		eighteen.setTime(today.getTime() - eighteenYearsBefore);
		if (date2.after(eighteen)) {
			erreurs.add("User is too young");
		}
		return erreurs;
	}
	
}
