package com.filrouge.ProjetFilRouge.entity;

public class Evaluation {

	private Integer id;
	private Integer note;
	private String commentaires;
	private Session session;
	private User user;

	public Evaluation(Integer id, Integer note, String commentaires,Session session, User user) {
		this.id = id;
		this.note = note;
		this.commentaires = commentaires;
		this.session = session;
		this.user = user;
	}

	public Evaluation() {
	}

	public Evaluation(Integer note, String commentaires, User user) {
		this.note = note;
		this.commentaires = commentaires;
		this.user = user;
	}

	//GETTERS-SETTERS:
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getNote() {
		return note;
	}


	public void setNote(Integer note) {
		this.note = note;
	}


	public String getCommentaires() {
		return commentaires;
	}


	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
