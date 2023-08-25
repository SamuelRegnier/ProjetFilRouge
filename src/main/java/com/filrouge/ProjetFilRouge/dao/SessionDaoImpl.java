package com.filrouge.ProjetFilRouge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Classroom;
import com.filrouge.ProjetFilRouge.entity.Matter;
import com.filrouge.ProjetFilRouge.entity.Session;
import com.filrouge.ProjetFilRouge.entity.Training;
import com.filrouge.ProjetFilRouge.utils.UtilDate;

public class SessionDaoImpl implements SessionDao{


	private Connection connection; 
	
	public SessionDaoImpl (Connection connection) {
		this.connection=connection; 
	}
	
	protected TrainingDao trainingDao = new TrainingDaoImpl (connection);
	protected ClassroomDao classroomDao = new ClassroomDaoImpl (connection);
	protected MatterDao matterDao = new MatterDaoImpl (connection); 
	
	@Override
	public int add(Session session) {
		try {
			String query= "insert into session (date_debut , date_fin, nb_participants , id_training , id_classroom, id_matter) "
					+ "values (? , ? , ? ,?, ? , ?)";
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setDate(1 , UtilDate.changeDate(session.getDateDebut()));
			ps.setDate(2, UtilDate.changeDate(session.getDateFin()));
			ps.setInt(3, session.getNbParticipant());
			ps.setInt (4, session.getTraining().getId());
			ps.setInt(5, session.getClassroom().getId());
			ps.setInt(6, session.getMatter().getId());
			
			ps.executeUpdate();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0; 
	}

	@Override
	public void delete(Session session) {
		PreparedStatement pst; 

		try {
			String query = "Delete from session where id = ?" ; 
			pst = connection.prepareStatement(query);

			pst.setInt(1, session.getId());

			pst.executeUpdate();
			pst.close(); 

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public Session get(int id) {
		Session session = null ; 
		try {
			String query ="Select * from session "
					+ "inner join training on session.id_training = training.id "
					+ "inner join classroom on session.id_classroom = classroom.id "
					+ "inner join matter on session.id_matter=matter.id "
					+ "where session.id = ?" ; 
			
			PreparedStatement ps= connection.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer idsession = rs.getInt("id"); 
				Date dateDebut = rs.getDate("date_debut");
				Date dateFin = rs.getDate("date_debut");
				Integer nbParticipant = rs.getInt("nb_participants");
				Training training = new Training (rs.getInt("id_training")); 
				Classroom classroom = new Classroom(rs.getInt("id_classroom"));
				Matter matter = new Matter (rs.getInt("id_matter"));
				
				session = new Session (idsession, dateDebut, dateFin,nbParticipant, training , classroom , matter);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return session;
	}

		

	@Override
	public List<Session> getAll() {
		Statement statement; 
		List<Session>listSession = new ArrayList<>();

		try {
			String query = "Select * from session "
					+ "inner join training on session.id_training = training.id "
					+ "inner join classroom on session.id_classroom = classroom.id "
					+ "inner join matter on session.id_matter=matter.id ";
			
			statement = connection.createStatement(); 
			
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				System.out.println("je rentre");
				Integer id = result.getInt("id");
				Date dateDebut = result.getDate("date_debut");
				Date dateFin = result.getDate("date_fin");
				Integer nbParticipant = result.getInt("nb_participants");
				Training training = new Training (result.getInt("id_training")); 
				//on a crée un objet training et recuperer le resultat de id_training qui existe dans Training, qui lui meme existe dans Session
				Classroom classroom = new Classroom(result.getInt("id_classroom"));
				Matter matter = new Matter (result.getInt("id_matter"));
				listSession.add(new Session (id, dateDebut, dateFin,nbParticipant, training , classroom , matter));	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listSession;
	}

	@Override
	public void update(Session session) {
		try {
			String query= "Update session Set session.date_debut = ? , session.date_fin = ? , session.nb_participants = ?, "
					+ "session.id_training=? , session.id_classroom =? , session.id_matter =? "
					+ "where session.id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setDate(1 , UtilDate.changeDate(session.getDateDebut()));
			ps.setDate(2, UtilDate.changeDate(session.getDateFin()));
			ps.setInt(3, session.getNbParticipant());
			ps.setInt (4, session.getTraining().getId());
			ps.setInt(5, session.getClassroom().getId());
			ps.setInt(6, session.getMatter().getId());
			ps.setInt(7, session.getId());
			
			ps.executeUpdate();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
