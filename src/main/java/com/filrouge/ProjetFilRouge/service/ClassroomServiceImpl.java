package com.filrouge.ProjetFilRouge.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.filrouge.ProjetFilRouge.dao.ClassroomDaoImpl;
import com.filrouge.ProjetFilRouge.database.ConnectionDatabase;
import com.filrouge.ProjetFilRouge.entity.Classroom;
import com.filrouge.ProjetFilRouge.entity.Session;

@Service
public class ClassroomServiceImpl implements ClassroomService {
	com.filrouge.ProjetFilRouge.dao.ClassroomDao classroomDao;
	Connection connection;
	
	ClassroomServiceImpl() {
		Connection connection = ConnectionDatabase.getConnection();
		classroomDao = new ClassroomDaoImpl(connection);
	}
	
	@Override
	public int add(Classroom classroom) {
		// TODO Auto-generated method stub
		return classroomDao.add(classroom);
	}

	@Override
	public Classroom get(int id) {
		// TODO Auto-generated method stub
		return classroomDao.get(id);
	}

	@Override
	public List<Classroom> getAll() {
		// TODO Auto-generated method stub
		return classroomDao.getAll();
	}

	@Override
	public void update(Classroom classroom) {
		// TODO Auto-generated method stub
		classroomDao.update(classroom);
	}

	@Override
	public void delete(Classroom classroom) {
		// TODO Auto-generated method stub
		classroomDao.delete(classroom);
	}
	
	@Override
	public List<Classroom> getAllFreeClassrooms(Session session) {
		List<Classroom> freeClassrooms = new ArrayList<>();
		List<Classroom> Classrooms = classroomDao.getAll();
		for (Classroom classroom : Classrooms) {
			if (classroomDao.isFree(classroom, session.getDateDebut(), session.getDateFin(), session.getNbParticipant())) {
				freeClassrooms.add(classroom);
			}
		}
		return freeClassrooms;
	}
	
	@Override
	public Classroom getClassroomForSession(Session session) {
		return classroomDao.getClassroomForSession(session);
	}

	@Override
	public Classroom chooseClassroomForSession(Session session) {
		List<Classroom> freeClassrooms = this.getAllFreeClassrooms(session);
		Classroom chosenClassroom = null;
		for (Classroom classroom : freeClassrooms) {
			if (chosenClassroom == null) {
				chosenClassroom = classroom;
			} else if (chosenClassroom.getNbPlaces() > classroom.getNbPlaces()) {
				chosenClassroom = classroom;
			}
		}
		return chosenClassroom;
	}
	
	
}
