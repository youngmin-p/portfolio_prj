package com.propofol.www.user.portfolio.domain;

import java.util.List;

public class PortfolioViewSearch {
	private AboutMeSearch am_search;
	private TechStacksSearch ts_search;
	private TellMeSearch tm_search;
	private List<ExperienceSearch> expList;
	
	public PortfolioViewSearch() {
		
	} // PortfolioViewSearch

	public PortfolioViewSearch(AboutMeSearch am_search, TechStacksSearch ts_search, TellMeSearch tm_search,
			List<ExperienceSearch> expList) {
		this.am_search = am_search;
		this.ts_search = ts_search;
		this.tm_search = tm_search;
		this.expList = expList;
	} // PortfolioViewSearch

	public AboutMeSearch getAm_search() {
		return am_search;
	}

	public void setAm_search(AboutMeSearch am_search) {
		this.am_search = am_search;
	}

	public TechStacksSearch getTs_search() {
		return ts_search;
	}

	public void setTs_search(TechStacksSearch ts_search) {
		this.ts_search = ts_search;
	}

	public TellMeSearch getTm_search() {
		return tm_search;
	}

	public void setTm_search(TellMeSearch tm_search) {
		this.tm_search = tm_search;
	}

	public List<ExperienceSearch> getExpList() {
		return expList;
	}

	public void setExpList(List<ExperienceSearch> expList) {
		this.expList = expList;
	}

	@Override
	public String toString() {
		return "PortfolioViewSearch [am_search=" + am_search + ", ts_search=" + ts_search + ", tm_search=" + tm_search
				+ ", expList=" + expList + "]";
	}
	
} // class
