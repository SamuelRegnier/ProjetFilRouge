package com.filrouge.ProjetFilRouge.service;

import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Matter;
import com.filrouge.ProjetFilRouge.entity.Theme;

public interface MatterService {

	public int add (Matter matter, Theme theme); 
	public List<Matter> getAll();
	public Matter get(int id);
	public void delete(Matter matter);
	public void update (Matter matter);
	public void updateTheme(Matter matter, Theme theme);
	public void addTheme(Matter matter, Theme theme);
	public void deleteTheme(Matter matter, Theme theme);
}

