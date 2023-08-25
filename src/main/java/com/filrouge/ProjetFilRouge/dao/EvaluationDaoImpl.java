package com.filrouge.ProjetFilRouge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.filrouge.ProjetFilRouge.entity.Evaluation;
import com.filrouge.ProjetFilRouge.entity.Session;
import com.filrouge.ProjetFilRouge.entity.Training;
import com.filrouge.ProjetFilRouge.entity.User;

public class EvaluationDaoImpl implements EvaluationDao {

	
	private Connection connection;
	protected SessionDao sessionDao = new SessionDaoImpl(connection);
	protected UserDao userDao = new UserDaoImpl(connection);
	
	public EvaluationDaoImpl(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public int add(Evaluation evaluation) {
		int nbLignesExecutees = 0;
		
		try {
			
			String query = "insert into evaluation(note, commentaires, id_session, id_user) VALUES (?, ?, ?, ?)";
	        PreparedStatement ps = connection.prepareStatement(query);
		    
		    ps.setInt(1, evaluation.getNote());
		    ps.setString(2, evaluation.getCommentaires());
		    ps.setInt(3, evaluation.getSession().getId());
		    ps.setInt(4, evaluation.getUser().getId());
		    
		    
		    nbLignesExecutees = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nbLignesExecutees;
	}

	@Override
	public List<Evaluation> getAll() {
		List<Evaluation> listEvaluation = new ArrayList<>();
		
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM evaluation "
					+ "INNER JOIN session on session.id = evaluation.id_session "
					+ "INNER JOIN user on user.id = evaluation.id_user";
			
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				Integer id = result.getInt("id");
				Integer note = result.getInt("note");
				String commentaires = result.getString("commentaires");
				Session session = new Session(result.getInt("id_session"));
				User user = new User(result.getInt("id_user"));
				
				listEvaluation.add(new Evaluation(id, note, commentaires, session, user));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listEvaluation;
	}

	@Override
	public Evaluation get(int id) {
		Evaluation evaluation = null;

		try {
	        String query = "select * from evaluation "
	        		+ "INNER JOIN session on session.id = evaluation.id_session "
	        		+ "INNER JOIN user on user.id = evaluation.id_user where evaluation.id= ?";
	        PreparedStatement ps = connection.prepareStatement(query);

		    ps.setInt(1, id);
		    
		    ResultSet rs = ps.executeQuery();

		    while (rs.next()) {
		    	Integer id1 = rs.getInt("id");
		        Integer note = rs.getInt("note");
		        String commentaires = rs.getString("commentaires");
		        Session session = new Session(rs.getInt("id_session"));
		        User user = new User(rs.getInt("id_user"));
		        evaluation = new Evaluation(id1, note,commentaires,session,user);
		    }
		    
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return evaluation;
	}

	@Override
	public void update(Evaluation evaluation) {
		try {
	        String query = "update evaluation set note=?, commentaires= ? where id = ?";
		    PreparedStatement ps = connection.prepareStatement(query);
		    
		    ps.setInt(1, evaluation.getNote());
		    ps.setString(2, evaluation.getCommentaires());
		    ps.setInt(3, evaluation.getId());
		    ps.executeUpdate();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Evaluation evaluation) {
		try {
	        String query = "delete from evaluation where id =?";
	        PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, evaluation.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
    public List<Evaluation> getEvaluationBySession(int id) {
    List<Evaluation> listEvaluation = new ArrayList<>();

        try {

            String query = "select * from evaluation "
                    + "inner join session on evaluation.id_session=session.id "
                    + "inner join user on evaluation.id_user = user.id "
                    + "where session.id=?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                Integer note = result.getInt("note");
                String commentaires = result.getString("commentaires");
                User user = new User(result.getInt("id_user"));

                listEvaluation.add(new Evaluation(note, commentaires, user));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listEvaluation;
	}

}
