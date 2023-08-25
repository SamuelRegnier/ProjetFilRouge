package com.filrouge.ProjetFilRouge.entity;

import java.util.List;

public class Theme {

	private Integer id; 
	private String nom;
	private List<Matter> listMatter;
	
	public Theme(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	
	public Theme() { 
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

	public List<Matter> getListMatter() {
		return listMatter;
	}

	public void setListMatter(List<Matter> listMatter) {
		this.listMatter = listMatter;
	}

	
	
}

