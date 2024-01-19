package com.demo.gym.DTO;

public class ResponseGetUserDTO {
	private String resMsg;
	private String resCode;
	
	public ResponseGetUserDTO() {
		super();
	}
	public ResponseGetUserDTO(String resMsg, String resCode) {
		super();
		this.resMsg = resMsg;
		this.resCode = resCode;
	}
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	
	
}
