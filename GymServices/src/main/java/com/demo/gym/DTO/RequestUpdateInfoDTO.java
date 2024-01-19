package com.demo.gym.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestUpdateInfoDTO {
	private String name;
	private String cardNumber;
	private String email;
	@JsonProperty("CVV")
	private String CVV;
	private String expDate;
	private String nameCardHolder;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCVV() {
		return CVV;
	}
	public void setCVV(String cVV) {
		CVV = cVV;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getNameCardHolder() {
		return nameCardHolder;
	}
	public void setNameCardHolder(String nameCardHolder) {
		this.nameCardHolder = nameCardHolder;
	}
	
	
}
