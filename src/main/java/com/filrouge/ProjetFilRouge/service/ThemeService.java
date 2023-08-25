package com.filrouge.ProjetFilRouge.service;

import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Matter;
import com.filrouge.ProjetFilRouge.entity.Theme;



public interface ThemeService {

	public int add (Theme theme); 
	public List<Theme> getAll();
	public Theme get(int id);
	public void delete(Theme theme);
	public void update (Theme theme);
	public List<Matter> getAllByTheme(Theme theme);
	public List<Theme> getAllCategories();
		
}

