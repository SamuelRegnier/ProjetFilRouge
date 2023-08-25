package com.filrouge.ProjetFilRouge.dao;

import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Session;


public interface SessionDao {

	public int add(Session session);
	public void delete(Session session);
	public Session get (int id);
	public List <Session> getAll();
	public void update(Session session); 
}

