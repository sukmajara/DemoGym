package com.demo.gym.DTO;

public class ResponseRegisterUserDTO {
	private String resCode;
	private String resMsg;
	
	public ResponseRegisterUserDTO() {
		super();
	}
	public ResponseRegisterUserDTO(String resCode, String resMsg) {
		super();
		this.resCode = resCode;
		this.resMsg = resMsg;
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
	
	
	
}
