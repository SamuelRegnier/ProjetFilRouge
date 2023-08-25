package com.filrouge.ProjetFilRouge.dao;

import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Matter;
import com.filrouge.ProjetFilRouge.entity.Theme;



public interface ThemeDao {

	public int add (Theme theme); 
	public List<Theme> getAll();
	public Theme get(int id);
	public void delete(Theme theme);
	public void update (Theme theme);
	List<Matter> getAllMatters(Theme theme);
		
}

