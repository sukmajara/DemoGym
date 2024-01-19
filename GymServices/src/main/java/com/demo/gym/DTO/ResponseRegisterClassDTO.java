package com.demo.gym.DTO;

public class ResponseRegisterClassDTO {
	private String resCode;
	private String resMsg;

	public ResponseRegisterClassDTO() {
		super();
	}
	public ResponseRegisterClassDTO(String resCode, String resMsg) {
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
