package com.filrouge.ProjetFilRouge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ThemeMatterDaoImpl implements ThemeMatterDao {
	private Connection connection;
	protected TrainingDao trainingDao = new TrainingDaoImpl(connection);
	
	public ThemeMatterDaoImpl(Connection connection) {
		this.connection = connection;
	}
	@Override
	public int add(Integer id_theme, Integer id_matter) {
		PreparedStatement pst1;
		int nblignesExecutees = 0; 

		try {

			String query1 = "INSERT into theme_matter(id_theme, id_matter) VALUES (?, ?)";
			
			pst1 = connection.prepareStatement(query1);

			pst1.setInt(1, id_theme);
			pst1.setInt(2, id_matter);
			nblignesExecutees = pst1.executeUpdate();

			pst1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nblignesExecutees;
	}
	
	@Override
	public void delete(Integer id_theme, Integer id_matter) {
		PreparedStatement pst1;

		try {

			String query1 = "DELETE FROM theme_matter WHERE id_theme = ? AND id_matter = ?";
			
			pst1 = connection.prepareStatement(query1);

			pst1.setInt(1, id_theme);
			pst1.setInt(2, id_matter);
			pst1.executeUpdate();

			pst1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateTheme(Integer id_theme, Integer id_matter) {
		PreparedStatement pst1;

		try {

			String query1 = "UPDATE theme_matter SET id_theme = ? WHERE id_matter = ?";
			
			pst1 = connection.prepareStatement(query1);

			pst1.setInt(1, id_theme);
			pst1.setInt(2, id_matter);
			pst1.executeUpdate();

			pst1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
