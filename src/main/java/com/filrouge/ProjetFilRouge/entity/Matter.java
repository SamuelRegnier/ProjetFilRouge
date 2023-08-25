package com.filrouge.ProjetFilRouge.entity;

import java.util.List;

public class Matter {
	private Integer id; 
	private String nom;
	private List<Theme> listTheme;
	private List<Training> listTraining;
	
	
	public Matter(Integer id, String nom) {
		this.id = id;
		this.nom = nom;
	}
	
	public Matter() { 
	}
	
	public Matter(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Theme> getListTheme() {
		return listTheme;
	}

	public void setListTheme(List<Theme> listTheme) {
		this.listTheme = listTheme;
	} 

	public List<Training> getListTraining() {
		return listTraining;
	}

	public void setListTraining(List<Training> listTraining) {
		this.listTraining = listTraining;
	} 
	
	
}

