package com.filrouge.ProjetFilRouge.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.filrouge.ProjetFilRouge.dao.UserDao;
import com.filrouge.ProjetFilRouge.dao.UserDaoImpl;
import com.filrouge.ProjetFilRouge.database.ConnectionDatabase;
import com.filrouge.ProjetFilRouge.entity.User;

@Service
public class UserServiceImpl implements UserService{
	
	Connection connection;
	UserDao userDao;
	
	public UserServiceImpl() {
		connection = ConnectionDatabase.getConnection();
		userDao = new UserDaoImpl(connection);
	}

	@Override
	public int add(User use) {
		return userDao.add(use);
	}

	@Override
	public void delete(User use) {
		userDao.delete(use);		
	}

	@Override
	public void update(User use) {
		userDao.update(use);
	}

	@Override
	public User get(int id) {
		return userDao.get(id);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}
	
	@Override
	public Boolean isMailAlreadyInDatabase(String mail) {
		return userDao.getAllMails().contains(mail);
	}


}
