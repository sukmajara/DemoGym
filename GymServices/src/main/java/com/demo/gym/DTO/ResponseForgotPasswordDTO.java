package com.demo.gym.DTO;

public class ResponseForgotPasswordDTO {
	private String resMsg;
	private String resCode;
	
	public ResponseForgotPasswordDTO() {
		super();
	}
	public ResponseForgotPasswordDTO(String resMsg, String resCode) {
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
