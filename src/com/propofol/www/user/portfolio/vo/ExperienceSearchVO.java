package com.propofol.www.user.portfolio.vo;

public class ExperienceSearchVO {
	private String user_id, exp_cd;
	
	public ExperienceSearchVO() {
		
	} // ExperienceSearchVO
	
	public ExperienceSearchVO(String user_id, String exp_cd) {
		this.user_id = user_id;
		this.exp_cd = exp_cd;
	} // ExperienceSearchVO

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
	
} // class
