package com.propofol.www.user.portfolio.domain;

public class PortfolioListSearch {
	private String thumbnail_img, title, write_dt, user_id, hits;
	private int num;

	public String getThumbnail_img() {
		return thumbnail_img;
	}

	public void setThumbnail_img(String thumbnail_img) {
		this.thumbnail_img = thumbnail_img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getWrite_dt() {
		return write_dt;
	}

	public void setWrite_dt(String write_dt) {
		this.write_dt = write_dt;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getHits() {
		return hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "PortfolioListSearch [thumbnail_img=" + thumbnail_img + ", title=" + title + ", write_dt=" + write_dt
				+ ", user_id=" + user_id + ", hits=" + hits + ", num=" + num + "]";
	}
	
} // class
