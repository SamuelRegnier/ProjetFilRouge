package com.filrouge.ProjetFilRouge.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.filrouge.ProjetFilRouge.dao.ClassroomDao;
import com.filrouge.ProjetFilRouge.dao.ClassroomDaoImpl;
import com.filrouge.ProjetFilRouge.dao.MatterDao;
import com.filrouge.ProjetFilRouge.dao.MatterDaoImpl;
import com.filrouge.ProjetFilRouge.dao.SessionDao;
import com.filrouge.ProjetFilRouge.dao.SessionDaoImpl;
import com.filrouge.ProjetFilRouge.dao.TrainingDao;
import com.filrouge.ProjetFilRouge.dao.TrainingDaoImpl;
import com.filrouge.ProjetFilRouge.database.ConnectionDatabase;
import com.filrouge.ProjetFilRouge.entity.Session;

@Service
public class SessionServiceImpl  implements SessionService{

	SessionDao sessionDao;
	Connection connection;
	TrainingDao trainingDao;
	ClassroomDao classroomDao;
	MatterDao matterDao;
	
	//Spring en arriere plan:
	//SessionServuceImpl (SessionDao sessionDao, TrainingDao trainingDao, ....)
	//this.sessionDao=sessionDao;
	//this.trainingDao = trainingDao; 
	SessionServiceImpl () {
		Connection connection = ConnectionDatabase.getConnection();
		sessionDao = new SessionDaoImpl(connection);
		trainingDao = new TrainingDaoImpl(connection);
		classroomDao = new ClassroomDaoImpl(connection);
		matterDao = new MatterDaoImpl(connection); 
	}
	@Override
	public int add(Session session) {
		return sessionDao.add(session);
	}

	@Override
	public void delete(Session session) {
		sessionDao.delete(session);
	}

	@Override
	public Session get(int id) {
		Session session = sessionDao.get(id);
		if (session != null) {
	        session.setTraining(trainingDao.get(session.getTraining().getId()));
	        session.setClassroom(classroomDao.get(session.getClassroom().getId()));
	        session.setMatter(matterDao.get(session.getMatter().getId()));
	    	}
		
	    return session;
	}


	@Override
	public List<Session> getAll() {
		List<Session> listSession = sessionDao.getAll();
		for (Session session : listSession) {
			session.setTraining(trainingDao.get(session.getTraining().getId()));
			session.setClassroom(classroomDao.get(session.getClassroom().getId()));
			session.setMatter(matterDao.get(session.getMatter().getId()));	
		}
		//on recupère uniquement l'Id de training. pour recuperer le reste des info de la table trainig lié à la table session, on fait une boucle for each, 
		// puis on appelle la methode set de session, trainingDao pour recuperer training de session et son id de trainig et le reste des info de la table training
		
		return listSession;
	}

	@Override
	public void update(Session session) {
		//trainingDao.update(session.getTraining());
		//classroomDao.update(session.getClassroom());
		//matterDao.update(session.getMatter());
		sessionDao.update(session);
		
	}

	@Override
	public Session getSessionWithEval() {
		// TODO Auto-generated method stub
		return null;
	}

}
