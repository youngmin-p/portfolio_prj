package com.propofol.www.user.portfolio.vo;

public class MyPortfolioVO {
	private String user_id, thumbnail_img, title, public_st;
	
	public MyPortfolioVO() {
		
	} // MyPortfolioVO
	
	public MyPortfolioVO(String user_id, String thumbnail_img, String title, String public_st) {
		this.user_id = user_id;
		this.thumbnail_img = thumbnail_img;
		this.title = title;
		this.public_st = public_st;
	} // MyPortfolioVO

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

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

	public String getPublic_st() {
		return public_st;
	}

	public void setPublic_st(String public_st) {
		this.public_st = public_st;
	}
	
} // class
