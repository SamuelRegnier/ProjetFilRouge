package com.filrouge.ProjetFilRouge.dao;

import java.util.List;

import com.filrouge.ProjetFilRouge.entity.User;

public interface UserDao {
	public int add(User use);
	public User get(int id);
	public List<User> getAll();
	public void update(User use);
	public void delete(User use);
	public List<String> getAllMails();
	public List<String> getAllNumTel();
}
 