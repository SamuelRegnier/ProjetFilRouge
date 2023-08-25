package com.filrouge.ProjetFilRouge.dao;

public interface ThemeMatterDao {

	public int add (Integer id_theme, Integer id_matter); 
	public void delete(Integer id_theme, Integer id_matter);
	public void updateTheme(Integer id_theme, Integer id_matter);
	
}

