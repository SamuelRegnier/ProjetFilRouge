package com.filrouge.ProjetFilRouge.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.filrouge.ProjetFilRouge.dao.TrainingDao;
import com.filrouge.ProjetFilRouge.dao.TrainingDaoImpl;
import com.filrouge.ProjetFilRouge.database.ConnectionDatabase;
import com.filrouge.ProjetFilRouge.entity.Training;

@Service
public class TrainingServiceImpl implements TrainingService {
	
	Connection connection;
	TrainingDao trainingDao;
	
	public TrainingServiceImpl() {
		connection = ConnectionDatabase.getConnection();
		trainingDao = new TrainingDaoImpl(connection);
	}

	@Override
	public int add(Training training) {
		return trainingDao.add(training);
	}

	@Override
	public Training get(int id) {
		return trainingDao.get(id);
	}

	@Override
	public List<Training> getAll() {
		return trainingDao.getAll();
	}

	@Override
	public void delete(Training training) {
		trainingDao.delete(training);
	}

	@Override
	public void update(Training training) {
		trainingDao.update(training);	
	}

}
