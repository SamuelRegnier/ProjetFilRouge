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
import com.filrouge.ProjetFilRouge.entity.ThemeMatter;
import com.filrouge.ProjetFilRouge.exception.NotFoundException;
import com.filrouge.ProjetFilRouge.service.MatterService;

@RestController
@RequestMapping("/api")
public class MatterController {
	
	private MatterService matterService;
	
	public MatterController(MatterService matterService) {
		this.matterService = matterService;
	}
	
	
	@GetMapping("/matters")
	public List<Matter> findAll() {
		return matterService.getAll();
	}
	
	@GetMapping("/matters/{matterId}")
	public Matter getMessageById(@PathVariable int matterId) {
		Matter matter = matterService.get(matterId);
		if (matter == null) {
			throw new NotFoundException("Matter with id " + matterId + " does not exist.");
		}
		return matter;
	}
	
	@PostMapping(value = "/matters")
	public Matter addMatter(@RequestBody ThemeMatter themematter) {
		Matter matter = themematter.getMatter();
		Theme theme = themematter.getTheme();
		// On souhaite qu'une matière soit TOUJOURS associée à un thème.
		matter.setId(null);
		matterService.add(matter, theme);
		return matter;
	}
	
	@PostMapping(value = "/mattersThemes")
	public Matter addTheme(@RequestBody ThemeMatter themematter) {
		Matter matter = themematter.getMatter();
		Theme theme = themematter.getTheme();
		// Dans le schéma relationnel, on a mis en évidence le fait qu'une matière puisse appartenir à plusieurs thèmes.
		// Cette fonction sert à rajouter un thème à une matière.
		matterService.addTheme(matter, theme);
		return themematter.getMatter();
	}
	
	
	@PutMapping("/matters")
	public Matter updateMatter(@RequestBody Matter matter) {
		matterService.update(matter);
		return matter;
	}
	
	@PutMapping("/mattersThemes")
	public Matter updateTheme(@RequestBody ThemeMatter themematter) {
		Matter matter = themematter.getMatter();
		Theme theme = themematter.getTheme();
		// Nom ambigüe aux premiers abords, mais il va modifier uniquement le thème dans une relation matière-thème.
		matterService.updateTheme(matter, theme);
		return matter;
	}
	
	@DeleteMapping("/matters/{matterId}")
	public String deleteMatter(@PathVariable int matterId) {
		// Dans la couche service, on fait en sorte que TOUTES les relations entre thèmes et cette matière soient
		// supprimées en même temps que la matière. Cela diffère du thème qui ne peut être supprimé que s'il
		// ne possède aucune matière associée à lui.
		Matter tempMatter = matterService.get(matterId);
		if (tempMatter == null) {
			throw new RuntimeException("His message ID not found - " + matterId);
		}
		matterService.delete(tempMatter);
		return "Deleted matter with id - " + matterId;
	}
	
	@DeleteMapping("/mattersThemes")
	public String deleteTheme(@RequestBody ThemeMatter themematter) {
		Matter matter = themematter.getMatter();
		Theme theme = themematter.getTheme();
		// Si on veut sup. une relation thème-matière pour cette matière. 
		matterService.deleteTheme(matter, theme);
		return "Deleted relation between matter with id - " + matter.getId() + " and theme with id - " + theme.getId();
	}
}
