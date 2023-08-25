package com.filrouge.ProjetFilRouge.service;

import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Training;

public interface TrainingService {
	public int add(Training training);
	public Training get(int id);
	public List<Training> getAll();
	public void delete(Training training);
	public void update(Training training);

}
