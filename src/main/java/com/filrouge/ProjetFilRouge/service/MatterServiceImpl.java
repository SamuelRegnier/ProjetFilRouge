package com.filrouge.ProjetFilRouge.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.filrouge.ProjetFilRouge.dao.MatterDaoImpl;
import com.filrouge.ProjetFilRouge.dao.ThemeMatterDaoImpl;
import com.filrouge.ProjetFilRouge.database.ConnectionDatabase;
import com.filrouge.ProjetFilRouge.entity.Matter;
import com.filrouge.ProjetFilRouge.entity.Theme;

@Service
public class MatterServiceImpl implements MatterService {
	com.filrouge.ProjetFilRouge.dao.MatterDao matterDao;
	com.filrouge.ProjetFilRouge.dao.ThemeMatterDao themeMatterDao;
	Connection connection;
	
	MatterServiceImpl() {
		Connection connection = ConnectionDatabase.getConnection();
		matterDao = new MatterDaoImpl(connection);
		themeMatterDao = new ThemeMatterDaoImpl(connection);
	}
	
	@Override
	public int add(Matter matter, Theme theme) {
		themeMatterDao.add(theme.getId(), matter.getId());
		return matterDao.add(matter);
	}

	@Override
	public List<Matter> getAll() {
		// TODO Auto-generated method stub
		return matterDao.getAll();
	}

	@Override
	public Matter get(int id) {
		// TODO Auto-generated method stub
		return matterDao.get(id);
	}

	@Override
	public void delete(Matter matter) {
		// Faire attention, il faut d'abord supprimer toutes les relations matière-thème associées à cette matière.
		List<Theme> listThemes = matterDao.getAllThemes(matter);
		for (Theme theme : listThemes) {
			themeMatterDao.delete(theme.getId(), matter.getId());
		}
		matterDao.delete(matter);
	}
	
	@Override
	public void deleteTheme(Matter matter, Theme theme) {
		themeMatterDao.delete(theme.getId(), matter.getId());
	}
	
	@Override
	public void update(Matter matter) {
		// TODO Auto-generated method stub
		matterDao.update(matter);
	}

	@Override
	public void updateTheme(Matter matter, Theme theme) {
		themeMatterDao.updateTheme(theme.getId(), matter.getId());
	}
	
	@Override
	public void addTheme(Matter matter, Theme theme) {
		themeMatterDao.add(theme.getId(), matter.getId());
	}
}
