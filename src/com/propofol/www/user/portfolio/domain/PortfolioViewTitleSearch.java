package com.propofol.www.user.portfolio.domain;

public class PortfolioViewTitleSearch {
	private String title, user_id, write_dt;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getWrite_dt() {
		return write_dt;
	}

	public void setWrite_dt(String write_dt) {
		this.write_dt = write_dt;
	}

	@Override
	public String toString() {
		return "PortfolioViewTitleSearch [title=" + title + ", user_id=" + user_id + ", write_dt=" + write_dt + "]";
	}
	
} // class
