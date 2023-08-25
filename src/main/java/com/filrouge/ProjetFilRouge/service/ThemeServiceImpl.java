package com.filrouge.ProjetFilRouge.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.filrouge.ProjetFilRouge.dao.MatterDaoImpl;
import com.filrouge.ProjetFilRouge.dao.ThemeDaoImpl;
import com.filrouge.ProjetFilRouge.database.ConnectionDatabase;
import com.filrouge.ProjetFilRouge.entity.Matter;
import com.filrouge.ProjetFilRouge.entity.Theme;

@Service
public class ThemeServiceImpl implements ThemeService {
	com.filrouge.ProjetFilRouge.dao.ThemeDao themeDao;
	com.filrouge.ProjetFilRouge.dao.MatterDao matterDao;
	Connection connection;
	
	ThemeServiceImpl() {
		Connection connection = ConnectionDatabase.getConnection();
		themeDao = new ThemeDaoImpl(connection);
		matterDao = new MatterDaoImpl(connection);
	}
	
	@Override
	public int add(Theme theme) {
		// TODO Auto-generated method stub
		return themeDao.add(theme);
	}

	@Override
	public List<Theme> getAll() {
		// TODO Auto-generated method stub
		return themeDao.getAll();
	}

	@Override
	public Theme get(int id) {
		// TODO Auto-generated method stub
		return themeDao.get(id);
	}

	@Override
	public void delete(Theme theme) {
		// TODO Auto-generated method stub
		themeDao.delete(theme);
	}

	@Override
	public void update(Theme theme) {
		// TODO Auto-generated method stub
		themeDao.update(theme);
	}

	@Override 
	public List<Matter> getAllByTheme(Theme theme) {
		return themeDao.getAllMatters(theme);
	}
	
	@Override
	public List<Theme> getAllCategories() {
		List<Theme> listTheme = themeDao.getAll();
		List<Theme> listTheme2 = new ArrayList<>();
		for (Theme theme : listTheme) {
			List<Matter> listMatter = new ArrayList<>();
			for (Matter matter : themeDao.getAllMatters(theme)) {
				matter.setListTraining(matterDao.getAllTrainings(matter));
				listMatter.add(matter);
			}
			theme.setListMatter(listMatter);
			listTheme2.add(theme);
		}
		return listTheme2;
	}
}
