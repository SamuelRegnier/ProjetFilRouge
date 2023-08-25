package com.filrouge.ProjetFilRouge.service;

import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Session;

public interface SessionService {
	int add(Session session);
	void delete(Session session);
	Session get (int id);
	List <Session> getAll();
	void update(Session session);
	Session getSessionWithEval (); 
}
