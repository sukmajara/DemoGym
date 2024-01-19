package com.demo.gym.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;


public class RequestRegisterUserDTO {

	private String name;
	
    @Email(message = "Please provide a valid email address")
	private String email;
	private String password;
	private String phoneNumber;
	private String cardNumber;
	@JsonProperty("CVV")
	private String CVV;
	private String expDate;
	private String nameCardHolder;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
