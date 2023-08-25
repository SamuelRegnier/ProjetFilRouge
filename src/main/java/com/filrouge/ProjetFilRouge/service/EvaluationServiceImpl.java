package com.filrouge.ProjetFilRouge.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.filrouge.ProjetFilRouge.dao.EvaluationDao;
import com.filrouge.ProjetFilRouge.dao.EvaluationDaoImpl;
import com.filrouge.ProjetFilRouge.dao.SessionDao;
import com.filrouge.ProjetFilRouge.dao.SessionDaoImpl;
import com.filrouge.ProjetFilRouge.dao.UserDao;
import com.filrouge.ProjetFilRouge.dao.UserDaoImpl;
import com.filrouge.ProjetFilRouge.database.ConnectionDatabase;
import com.filrouge.ProjetFilRouge.entity.Evaluation;
import com.filrouge.ProjetFilRouge.entity.Session;

@Service
public class EvaluationServiceImpl implements EvaluationService {
	
	Connection connection;
	EvaluationDao evaluationDao;
	SessionDao sessionDao;
	UserDao userDao;
	
	public EvaluationServiceImpl() {
		connection = ConnectionDatabase.getConnection();
		evaluationDao = new EvaluationDaoImpl(connection);
		sessionDao = new SessionDaoImpl(connection);
		userDao = new UserDaoImpl(connection);
	}

	@Override
	public int add(Evaluation eval) {
		return evaluationDao.add(eval);
	}

	@Override
	public void delete(Evaluation eval) {
		evaluationDao.delete(eval);
	}

	@Override
	public void update(Evaluation eval) {
		evaluationDao.update(eval);
	}

	@Override
	public Evaluation get(int id) {
		return evaluationDao.get(id);
	}

	@Override
	public List<Evaluation> getAll() {
		List<Evaluation> evals = evaluationDao.getAll();
		for(Evaluation eval : evals) {
			eval.setSession(sessionDao.get(eval.getSession().getId()));
			eval.setUser(userDao.get(eval.getUser().getId()));
		}
		return evals;
	}
	
	@Override
	public List<Evaluation> getEvaluationBySession(int id){
		List<Evaluation> evals = evaluationDao.getEvaluationBySession(id);
		for(Evaluation eval : evals) {
			eval.setUser(userDao.get(eval.getUser().getId()));
		}
		return evals;
	}

}
