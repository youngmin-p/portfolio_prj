package com.propofol.www.user.member.vo;

public class LoginVO {
	private String user_id, password;
	
	public LoginVO() {
		
	}

	public LoginVO(String user_id, String password) {
		this.user_id = user_id;
		this.password = password;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginVO [user_id=" + user_id + ", password=" + password + "]";
	}

} // class
