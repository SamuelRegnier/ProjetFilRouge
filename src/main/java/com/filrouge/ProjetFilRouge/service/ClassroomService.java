package com.filrouge.ProjetFilRouge.service;

import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Classroom;
import com.filrouge.ProjetFilRouge.entity.Session;

public interface ClassroomService {
	public int add(Classroom classroom);
	public Classroom get(int id);
	public List<Classroom> getAll();
	public void update(Classroom classroom);
	public void delete(Classroom classroom);
	public List<Classroom> getAllFreeClassrooms(Session session);
	public Classroom getClassroomForSession(Session session);
	public Classroom chooseClassroomForSession(Session session);
}
