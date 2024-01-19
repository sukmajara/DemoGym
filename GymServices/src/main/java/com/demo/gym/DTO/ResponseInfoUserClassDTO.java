package com.demo.gym.DTO;

import java.util.List;

import com.demo.gym.domain.GymClass;

public class ResponseInfoUserClassDTO {
	private String resCode;
	private String resMsg;
	private String status;
	private String email;
	private int quota;

	public ResponseInfoUserClassDTO() {
	}

	public ResponseInfoUserClassDTO(String resCode, String resMsg, String status, String email, int quota) {
		this.resCode = resCode;
		this.resMsg = resMsg;
		this.status = status;
		this.email = email;
		this.quota = quota;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}
}
