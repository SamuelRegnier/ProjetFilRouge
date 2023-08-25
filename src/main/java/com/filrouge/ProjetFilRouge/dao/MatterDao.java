package com.filrouge.ProjetFilRouge.dao;

import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Matter;
import com.filrouge.ProjetFilRouge.entity.Theme;
import com.filrouge.ProjetFilRouge.entity.Training;

public interface MatterDao {

	public int add (Matter matter); 
	public List<Matter> getAll();
	public Matter get(int id);
	public void delete(Matter matter);
	public void update (Matter matter);
	public List<Training> getAllTrainings(Matter matter);
	public List<Theme> getAllThemes(Matter matter);
		
}

