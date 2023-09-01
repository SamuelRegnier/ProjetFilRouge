package com.filrouge.ProjetFilRouge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filrouge.ProjetFilRouge.entity.User;
import com.filrouge.ProjetFilRouge.erreur.ErreurResponse;
import com.filrouge.ProjetFilRouge.exception.NotFoundException;
import com.filrouge.ProjetFilRouge.exception.StringException;
import com.filrouge.ProjetFilRouge.service.UserService;
import com.filrouge.ProjetFilRouge.validation.MailValidation;
import com.filrouge.ProjetFilRouge.validation.StringValidation;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public List<User> findAll(){
		return userService.getAll();
	}
	
	@GetMapping("/users/{userId}")
	public User findById(@PathVariable int userId) {
		User user = userService.get(userId);
		return user;
	}
	
	@PostMapping(value = "/users")
	public User add(@RequestBody User user) {
		user.setId(null);
		List<String> erreurs = StringValidation.erreurNom(user.getNom());
		erreurs.addAll(StringValidation.erreurPrenom(user.getPrenom()));
		erreurs.addAll(StringValidation.erreurAdresse(user.getAdresse()));
		erreurs.addAll(MailValidation.isMailValid(user.getMail(), userService));
		erreurs.addAll(StringValidation.erreurStatut(user.getStatut()));
		if (!erreurs.isEmpty()) {
			throw new StringException(erreurs);
		}
		userService.add(user);
		return user;
	}
	
	@DeleteMapping("/users/{userId}")
	public String delete(@PathVariable int userId) {
		User user = userService.get(userId);
		if (user == null) {
			throw new RuntimeException("Id " + userId +  " non trouvé.");
		}
		userService.delete(user);
		return "Utilisateur " + userId + " a bien été supprimé";
	}
	
	@PutMapping("/users")
	public User update(@RequestBody User user) {
		userService.update(user);
		return user;
	}
	
	@ExceptionHandler
	public ResponseEntity<ErreurResponse> handleException(StringException e) {
	ErreurResponse error = new ErreurResponse();
	error.setStatus(HttpStatus.NOT_FOUND.value());
	error.setMessage(e.getMessage());
	error.setTimeStamp(System.currentTimeMillis());
	return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
