package com.demo.gym.DTO;

public class ResponseResendTokenDTO {

	private String resCode;
	private String resMsg;
	
	public ResponseResendTokenDTO() {
		super();
	}
	public ResponseResendTokenDTO(String resCode, String resMsg) {
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
