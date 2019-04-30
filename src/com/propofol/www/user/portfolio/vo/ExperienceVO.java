package com.propofol.www.user.portfolio.vo;

public class ExperienceVO {
	private String user_id, exp_cd, title, contents, upload_img;
	
	public ExperienceVO() {
		
	} // ExperienceVO
	
	public ExperienceVO(String user_id, String exp_cd, String title, String contents, String upload_img) {
		this.user_id = user_id;
		this.exp_cd = exp_cd;
		this.title = title;
		this.contents = contents;
		this.upload_img = upload_img;
	} // ExperienceVO

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getExp_cd() {
		return exp_cd;
	}

	public void setExp_cd(String exp_cd) {
		this.exp_cd = exp_cd;
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
