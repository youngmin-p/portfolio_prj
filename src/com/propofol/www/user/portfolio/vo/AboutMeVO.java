package com.propofol.www.user.portfolio.vo;

public class AboutMeVO {
	private String user_id, title, contents, upload_img;
	
	public AboutMeVO() {
		
	} // AboutMeVO

	public AboutMeVO(String user_id, String title, String contents, String upload_img) {
		this.user_id = user_id;
		this.title = title;
		this.contents = contents;
		this.upload_img = upload_img;
	} // AboutMeVO
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getUpload_img() {
		return upload_img;
	}

	public void setUpload_img(String upload_img) {
		this.upload_img = upload_img;
	}
	
} // class
