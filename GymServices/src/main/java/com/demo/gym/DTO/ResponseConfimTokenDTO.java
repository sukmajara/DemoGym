package com.demo.gym.DTO;

public class ResponseConfimTokenDTO {

	private String resMsg;
	private String resCode;
	
	public ResponseConfimTokenDTO() {
		super();
	}
	public ResponseConfimTokenDTO(String resMsg, String resCode) {
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
