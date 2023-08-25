package com.filrouge.ProjetFilRouge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Matter;
import com.filrouge.ProjetFilRouge.entity.Theme;
import com.filrouge.ProjetFilRouge.entity.Training;


public class MatterDaoImpl implements MatterDao {

	private Connection connection; 


	public MatterDaoImpl (Connection connection) {
		this.connection=connection; 
	}

	@Override
	public int add(Matter matter) {
		PreparedStatement pst1;
		int nblignesExecutees = 0; 

		try {

			String query1 = "INSERT into matter(nom) VALUES (?)";
			
			pst1 = connection.prepareStatement(query1);

			pst1.setString(1, matter.getNom());
			nblignesExecutees = pst1.executeUpdate();

			pst1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nblignesExecutees;
	}

	@Override
	public List<Matter> getAll() {
		Statement statement2; 
		List<Matter>listMatter = new ArrayList<>();

		try {
			String query2 = "SELECT * FROM matter";
			
			statement2 = connection.createStatement(); 
			
			ResultSet result2 = statement2.executeQuery(query2);

			while (result2.next()) {
				Integer id = result2.getInt ("id"); 
				String nom = result2.getString("nom");

				listMatter.add(new Matter (id, nom));	
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listMatter;
	}

	@Override
	public Matter get(int id) {
		Matter matter  = null;
		Statement statement3; 

		try {
			String query3 = "SELECT * FROM matter WHERE id = " + id; 
			
			statement3 = connection.createStatement();
			
			ResultSet result3 = statement3.executeQuery(query3);

			while (result3.next()) {
				Integer idMatter = result3.getInt("id");
				String nom = result3.getString("nom");


				matter  = (new Matter (idMatter, nom));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matter;
	}

	@Override
	public void delete(Matter matter) {
		PreparedStatement pst4; 

		try {
			String query4 = "DELETE from matter where id = ?" ; 
			pst4 = connection.prepareStatement(query4);

			pst4.setInt(1, matter.getId());

			pst4.executeUpdate();
			pst4.close(); 

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Matter matter) {
		PreparedStatement pst5 = null ;

		try {

			String query5 = "UPDATE matter SET nom = ? where id = ?"; 
			
			pst5 = connection.prepareStatement(query5);
			
			pst5.setString(1, matter.getNom());
			pst5.setInt(2, matter.getId());
			

			pst5.executeUpdate(); 
			pst5.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Training> getAllTrainings(Matter matter) {
		PreparedStatement statement2; 
		List<Training> listTraining = new ArrayList<>();

		try {
			String query2 = "SELECT DISTINCT training.* FROM training JOIN session ON training.id = session.id_training " + 
					"JOIN matter ON matter.id = session.id_matter WHERE matter.id = ?";
			
			statement2 = connection.prepareStatement(query2); 
			
			statement2.setInt(1, matter.getId());
			ResultSet result = statement2.executeQuery();

			while (result.next()) {
				Integer id = result.getInt("id");
				String nom = result.getString("nom");
				Integer duree = result.getInt("duree");
				Integer nbParticipants = result.getInt("nb_Participants");
				Boolean certifiante = result.getBoolean("certifiante");
				Boolean prerequis = result.getBoolean("prerequis");
				Integer prix = result.getInt("prix");
				String description = result.getString("description");
				listTraining.add(new Training(id, nom, duree, nbParticipants, certifiante, prerequis, prix, description));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listTraining;
	}
	
	@Override
	public List<Theme> getAllThemes(Matter matter) {
		PreparedStatement statement2; 
		List<Theme>listTheme = new ArrayList<>();

		try {
			String query2 = "SELECT * FROM theme JOIN theme_matter ON theme.id = theme_matter.id_theme " +
					"JOIN matter ON matter.id = theme_matter.id_matter WHERE matter.id = ?";
			
			statement2 = connection.prepareStatement(query2); 
			
			statement2.setInt(1, matter.getId());
			ResultSet result = statement2.executeQuery();

			while (result.next()) {
				Integer id = result.getInt ("id"); 
				String nom = result.getString("nom");

				listTheme.add(new Theme (id, nom));	
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listTheme;
	}
}
