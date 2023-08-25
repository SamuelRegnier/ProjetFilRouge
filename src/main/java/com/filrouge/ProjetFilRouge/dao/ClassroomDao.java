package com.filrouge.ProjetFilRouge.dao;

import java.util.Date;
import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Classroom;
import com.filrouge.ProjetFilRouge.entity.Session;

public interface ClassroomDao {
	public int add(Classroom classroom);
	public Classroom get(int id);
	public List<Classroom> getAll();
	public void update(Classroom classroom);
	public void delete(Classroom classroom);
	public boolean isFree(Classroom classroom, Date dateDebut, Date dateFin, Integer num);
	public Classroom getClassroomForSession(Session session);
}
