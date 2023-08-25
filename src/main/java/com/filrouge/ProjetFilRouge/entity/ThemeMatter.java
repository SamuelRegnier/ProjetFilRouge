package com.filrouge.ProjetFilRouge.entity;

public class ThemeMatter {
	private Theme theme;
	private Matter matter;
	
	
	public ThemeMatter(Theme theme, Matter matter) {
		super();
		this.theme = theme;
		this.matter = matter;
	}
	
	public Theme getTheme() {
		return theme;
	}
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	public Matter getMatter() {
		return matter;
	}
	public void setMatter(Matter matter) {
		this.matter = matter;
	}
	
	
}
