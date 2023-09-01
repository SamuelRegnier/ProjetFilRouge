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

import com.filrouge.ProjetFilRouge.entity.Matter;
import com.filrouge.ProjetFilRouge.entity.Theme;
import com.filrouge.ProjetFilRouge.exception.StringException;
import com.filrouge.ProjetFilRouge.service.ThemeService;
import com.filrouge.ProjetFilRouge.validation.StringValidation;

@RestController
@RequestMapping("/api")
public class ThemeController {
	
	private ThemeService themeService;
	
	public ThemeController(ThemeService themeService) {
		this.themeService = themeService;
	}
	
	
	@GetMapping("/themes")
	public List<Theme> findAll() {
		return themeService.getAllCategories();
	}
	
	@GetMapping("/themes/{themeId}")
	public Theme getMessageById(@PathVariable int themeId) {
		Theme theme = themeService.get(themeId);
		if (theme == null) {
			throw new RuntimeException("Message id not found - " + themeId);
		}
		return theme;
	}
	
	@PostMapping(value = "/themes")
	public Theme addTheme(@RequestBody Theme theme) {
		theme.setId(null);
		List<String> erreurs = StringValidation.erreurNom(theme.getNom());
		if (!erreurs.isEmpty()) {
			throw new StringException(erreurs);
		}
		themeService.add(theme);
		return theme;
	}
	
	@PutMapping("/themes")
	public Theme updateTheme(@RequestBody Theme theme) {
		themeService.update(theme);
		return theme;
	}
	
	@DeleteMapping("/themes/{themeId}")
	public String deleteTheme(@PathVariable int themeId) {
		Theme tempTheme = themeService.get(themeId);
		if (tempTheme == null) {
			throw new RuntimeException("His message ID not found - " + themeId);
		}
		themeService.delete(tempTheme);
		return "Deleted theme with id - " + themeId;
	}
	
	@GetMapping("/themes/getMatters/{themeId}")
	public List<Matter> getMatters(@PathVariable int themeId) {
		return themeService.getAllByTheme(themeService.get(themeId));
	}
}
