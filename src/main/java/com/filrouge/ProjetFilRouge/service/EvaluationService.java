package com.filrouge.ProjetFilRouge.service;

import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Evaluation;
import com.filrouge.ProjetFilRouge.entity.Session;

public interface EvaluationService {
	public int add(Evaluation eval);
	public void delete(Evaluation eval);
	public void update(Evaluation eval);
	public Evaluation get(int id);
	public List<Evaluation> getAll();
	public List<Evaluation> getEvaluationBySession(int id);
}
