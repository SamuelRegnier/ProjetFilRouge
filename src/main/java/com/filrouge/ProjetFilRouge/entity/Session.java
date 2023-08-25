package com.filrouge.ProjetFilRouge.entity;

import java.util.Date;
import java.util.List;

public class Session {

	private Integer id; 
	private Date dateDebut;
	private Date dateFin;
	private Integer nbParticipant;
	private List<Evaluation> evalutions;
	private Training training;
	private Classroom classroom;
	private Matter matter; 
	
	
	


	public Session(Integer id, Date dateDebut, Date dateFin, Integer nbParticipant, List<Evaluation> evalutions,
			Training training, Classroom classroom, Matter matter) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbParticipant = nbParticipant;
		this.evalutions = evalutions;
		this.training = training;
		this.classroom = classroom;
		this.matter = matter;
	}

		

	public Session(Date dateDebut, Date dateFin, Integer nbParticipant, List<Evaluation> evalutions, Training training,
			Classroom classroom, Matter matter) {
		
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbParticipant = nbParticipant;
		this.evalutions = evalutions;
		this.training = training;
		this.classroom = classroom;
		this.matter = matter;
	}

	


	public Session(Integer id, Date dateDebut, Date dateFin, Integer nbParticipant, Training training,
			Classroom classroom, Matter matter) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbParticipant = nbParticipant;
		this.training = training;
		this.classroom = classroom;
		this.matter = matter;
	}

	public Session(Date dateDebut, Date dateFin, Integer nbParticipant, Training training,
			Classroom classroom, Matter matter) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbParticipant = nbParticipant;
		this.training = training;
		this.classroom = classroom;
		this.matter = matter;
	}
	public Session() {
	
	}

	public Session(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Date getDateDebut() {
		return dateDebut;
	}



	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}



	public Date getDateFin() {
		return dateFin;
	}



	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}



	public Integer getNbParticipant() {
		return nbParticipant;
	}



	public void setNbParticipant(Integer nbParticipant) {
		this.nbParticipant = nbParticipant;
	}



	public List<Evaluation> getEvalutions() {
		return evalutions;
	}



	public void setEvalutions(List<Evaluation> evalutions) {
		this.evalutions = evalutions;
	}



	public Training getTraining() {
		return training;
	}



	public void setTraining(Training training) {
		this.training = training;
	}



	public Classroom getClassroom() {
		return classroom;
	}



	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}



	public Matter getMatter() {
		return matter;
	}



	public void setMatter(Matter matter) {
		this.matter = matter;
	}


	
}
	
	