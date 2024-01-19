package com.demo.gym.DTO;

public class ResponseLoginUserDTO {

	private String resCode;
	private String resMsg;
	private String email;
	private String jwt;
	
	public ResponseLoginUserDTO() {
		super();
	}
	public ResponseLoginUserDTO(String resCode, String resMsg, String email, String jwt) {
		super();
		this.resCode = resCode;
		this.resMsg = resMsg;
		this.email = email;
		this.jwt = jwt;
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
}
