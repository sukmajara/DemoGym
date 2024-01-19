package com.demo.gym.DTO;

public class RequestLoginUserDTO {
	private String email;
	private String Password;
	
	public RequestLoginUserDTO() {
		super();
	}
	public RequestLoginUserDTO(String email, String password) {
		super();
		this.email = email;
		Password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
}
