package com.filrouge.ProjetFilRouge.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.filrouge.ProjetFilRouge.service.UserService;
public class MailValidation {
	
	public static final Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");
	
	public static List<String> isMailValid(Object mail, UserService userService) {
		List<String> erreurs = new ArrayList<String>();
		if (mail == null) {
			erreurs.add("Date is null");
			return erreurs;
		}
		Matcher matcher = pattern.matcher((String) mail);
		if (!matcher.find()) {
			erreurs.add("Mail is not valid");
		}
		if (userService.isMailAlreadyInDatabase((String) mail)) {
			erreurs.add("Mail is already used");
		}
		return erreurs;
	}
	
	/*
	 * UserDao :
	 * 	@Override
	public List<String> getAllMails() {
		List<String> users = new ArrayList<>();
		try {
			Statement stm = connection.createStatement();
			String query = "SELECT mail FROM user";
			
			ResultSet result = stm.executeQuery(query);
			
			while(result.next()) {
				String mail = result.getString("mail");
				users.add(mail);
			}
			
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	 */
	
	/*
	 * UserService :
	 * 	@Override
	@Override
	public Boolean isMailAlreadyInDatabase(mail) {
		return userDao.getAllMails().contains(mail);
	} 
	 */
}
