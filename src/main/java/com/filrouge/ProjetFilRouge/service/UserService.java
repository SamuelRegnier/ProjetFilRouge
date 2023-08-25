package com.filrouge.ProjetFilRouge.service;

import java.util.List;

import com.filrouge.ProjetFilRouge.entity.User;

public interface UserService {
	public int add(User use);
	public void delete(User use);
	public void update(User use);
	public User get(int id);
	public List<User> getAll();
	public Boolean isMailAlreadyInDatabase(String mail);
}
