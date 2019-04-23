package com.propofol.www.user.portfolio.domain;

import java.util.List;

public class PortfolioViewSearch {
	private String[] selected_technique;
	private List<AboutMeSearch> amList;
	private List<TellMeSearch> tmList;
	private List<ExperienceSearch> expList;
	
	public String[] getSelected_technique() {
		return selected_technique;
	}
	
	public void setSelected_technique(String[] selected_technique) {
		this.selected_technique = selected_technique;
	}
	
	public List<AboutMeSearch> getAmList() {
		return amList;
	}
	
	public void setAmList(List<AboutMeSearch> amList) {
		this.amList = amList;
	}
	
	public List<TellMeSearch> getTmList() {
		return tmList;
	}
	
	public void setTmList(List<TellMeSearch> tmList) {
		this.tmList = tmList;
	}
	
	public List<ExperienceSearch> getExpList() {
		return expList;
	}
	
	public void setExpList(List<ExperienceSearch> expList) {
		this.expList = expList;
	}
	
} // class
