package com.filrouge.ProjetFilRouge.dao;

import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Evaluation;
import com.filrouge.ProjetFilRouge.entity.Session;

public interface EvaluationDao {
	
	public int add(Evaluation evaluation);
	public List<Evaluation> getAll();
	public Evaluation get(int id);
	public void update(Evaluation evaluation);
	public void delete(Evaluation evaluation);
	public List<Evaluation> getEvaluationBySession(int id);
}
