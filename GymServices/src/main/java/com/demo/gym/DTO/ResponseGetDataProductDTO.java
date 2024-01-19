package com.demo.gym.DTO;

import java.util.List;

import com.demo.gym.domain.Product;

public class ResponseGetDataProductDTO {
	private String resCode;
	private String resMsg;
	private List<Product> product;
	
	public ResponseGetDataProductDTO() {
		super();
	}
	public ResponseGetDataProductDTO(String resCode, String resMsg, List<Product> product) {
		super();
		this.resCode = resCode;
		this.resMsg = resMsg;
		this.product = product;
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
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	
	
	
	
}
