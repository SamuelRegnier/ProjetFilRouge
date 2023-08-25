package com.filrouge.ProjetFilRouge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filrouge.ProjetFilRouge.entity.User;
import com.filrouge.ProjetFilRouge.service.UserService;
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
	public List<String> add(@RequestBody User user) {
		user.setId(null);
		List<String> erreurs = StringValidation.erreurNom(user.getNom());
		if (erreurs != null) {
			return erreurs;
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

}
