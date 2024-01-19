package com.demo.gym.DTO;

public class ResponseUpdateClassDTO {
	private String resCode;
	private String resMsg;
	private String product;
	int quota;
	
	
	public ResponseUpdateClassDTO() {
		super();
	}
	public ResponseUpdateClassDTO(String resCode, String resMsg, String product, int quota) {
		super();
		this.resCode = resCode;
		this.resMsg = resMsg;
		this.product = product;
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
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getQuota() {
		return quota;
	}
	public void setQuota(int quota) {
		this.quota = quota;
	}
	
	
}
