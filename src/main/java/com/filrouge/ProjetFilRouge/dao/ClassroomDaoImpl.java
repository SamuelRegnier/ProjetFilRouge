package com.filrouge.ProjetFilRouge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Classroom;
import com.filrouge.ProjetFilRouge.entity.Session;

public class ClassroomDaoImpl implements ClassroomDao{
	Connection connection;
	
	public ClassroomDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public int add(Classroom classroom) {
		int nbLignesCreees = 0;
		try {
			String query = "INSERT INTO classroom (nom, nb_places, statut) VALUES (?, ?, ?)";
			PreparedStatement stm = connection.prepareStatement(query);
			
			stm.setString(1, classroom.getNom());
			stm.setInt(2, classroom.getNbPlaces());
			stm.setString(3, classroom.getStatut());
			
			nbLignesCreees = stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nbLignesCreees;
	}

	@Override
	public Classroom get(int id) {
		Classroom classroom = null;
		try {
			Statement stm = connection.createStatement();
			String query = "SELECT * FROM classroom WHERE id = " + id;
			
			ResultSet result = stm.executeQuery(query);
			
			while(result.next()) {
				Integer id1 = result.getInt("id");
				String nom = result.getString("nom");
				Integer nbPlaces = result.getInt("nb_places");
				String statut = result.getString("statut");
				classroom = (new Classroom(id1, nom, nbPlaces, statut));
			}
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classroom;
	}

	@Override
	public List<Classroom> getAll() {
		List<Classroom> classrooms = new ArrayList<>();
		try {
			Statement stm = connection.createStatement();
			String query = "SELECT * FROM classroom";
			
			ResultSet result = stm.executeQuery(query);
			
			while(result.next()) {
				Integer id = result.getInt("id");
				String nom = result.getString("nom");
				Integer nbPlaces = result.getInt("nb_places");
				String statut = result.getString("statut");
				classrooms.add(new Classroom(id, nom, nbPlaces, statut));
			}
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classrooms;
	}

	@Override
	public void update(Classroom classroom) {
		try {
			String query = "UPDATE classroom SET nom=?, nb_places=?, statut=? WHERE id=?";
			PreparedStatement stm = connection.prepareStatement(query);
			
			stm.setString(1, classroom.getNom());
			stm.setInt(2, classroom.getNbPlaces());
			stm.setString(3, classroom.getStatut());
			stm.setInt(4, classroom.getId());
			
			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Classroom classroom) {
		try {
			String query = "DELETE FROM classroom WHERE id=?";
			PreparedStatement stm = connection.prepareStatement(query);
			
			stm.setInt(1, classroom.getId());
			
			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Fonction pour savoir si une salle est libre pour une période donnée [dateDebut, dateFin]
	@Override
	public boolean isFree(Classroom classroom, Date dateDebut, Date dateFin, Integer num) {
		if (num > classroom.getNbPlaces()) {
			return false;
		}
		try {
			String query = "SELECT * FROM session WHERE id_classroom = ?";
			PreparedStatement stm = connection.prepareStatement(query);
			
			stm.setInt(1, classroom.getId());
			
			ResultSet result = stm.executeQuery();

			List<Date> datesDebut = new ArrayList<>();
			List<Date> datesFin = new ArrayList<>();
			while (result.next()) {
				datesDebut.add(result.getDate("date_debut"));
				datesFin.add(result.getDate("date_fin"));
			}

			for (Date date : datesDebut) {
				if (dateDebut.before(date) && dateFin.after(date)) {
					return false;
				}
				if (dateDebut.equals(date) || dateFin.equals(date)) {
					return false;
				}
			}
			for (Date date : datesFin) {
				if (dateDebut.before(date) && dateFin.after(date)) {
					return false;
				}
				if (dateDebut.equals(date) || dateFin.equals(date)) {
					return false;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
		
	}
	
	@Override
	public Classroom getClassroomForSession(Session session) {
		try {
			String query = "SELECT * FROM classroom JOIN session ON session.id_classroom = classroom.id "
					+ "WHERE session.id = ?";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, session.getId());
			
			ResultSet result = stm.executeQuery();
			
			while(result.next()) {
				Integer id = result.getInt("classroom.id");
				String nom = result.getString("classroom.nom");
				Integer nbPlaces = result.getInt("classroom.nb_places");
				String statut = result.getString("classroom.statut");
				return new Classroom(id, nom, nbPlaces, statut);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Classroom();
	}
}
