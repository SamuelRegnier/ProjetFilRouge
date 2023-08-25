package com.filrouge.ProjetFilRouge.dao;

import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Training;

public interface TrainingDao {
	
	public int add(Training training);
	public List<Training> getAll();
	public Training get(int id);
	public void update(Training training);
	public void delete(Training training);
}
