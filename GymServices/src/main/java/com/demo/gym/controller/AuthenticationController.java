package com.demo.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.gym.DTO.RequestConfirmTokenDTO;
import com.demo.gym.DTO.RequestGetUserDTO;
import com.demo.gym.DTO.RequestLoginUserDTO;
import com.demo.gym.DTO.RequestRegisterUserDTO;
import com.demo.gym.DTO.RequestResendTokenDTO;
import com.demo.gym.DTO.RequestResetPasswordDTO;
import com.demo.gym.DTO.ResponseConfimTokenDTO;
import com.demo.gym.DTO.ResponseGetUserDTO;
import com.demo.gym.DTO.ResponseLoginUserDTO;
import com.demo.gym.DTO.ResponseRegisterUserDTO;
import com.demo.gym.DTO.ResponseResendTokenDTO;
import com.demo.gym.DTO.ResponseResetPasswordDTO;
import com.demo.gym.service.AuthencticationService;

@RestController
@RequestMapping("/user")
public class AuthenticationController {
	
	@Autowired
	private AuthencticationService authenticationService;	
	
	@PostMapping("/register")
	public ResponseRegisterUserDTO registerUser(@RequestBody RequestRegisterUserDTO register){
		return authenticationService.registerUser(register);
	}
		
	@PostMapping("/login")
	public ResponseLoginUserDTO loginUser(@RequestBody RequestLoginUserDTO loginRequest) {
		return authenticationService.loginUser(loginRequest);
	}
	
	@PostMapping("/confirmtoken")
	public ResponseEntity<ResponseConfimTokenDTO>  confirmToken(@RequestBody RequestConfirmTokenDTO request) {
		return authenticationService.confirmToken(request);
	}
	
	@PostMapping("/resendtoken")
	public ResponseEntity<ResponseResendTokenDTO>  resendToken(@RequestBody RequestResendTokenDTO request) {
		return authenticationService.resendToken(request);
	}
	
	@PostMapping("/resetpassword")
	public ResponseEntity<ResponseResetPasswordDTO> resetPassword(@RequestBody RequestResetPasswordDTO request){
		return authenticationService.resetPassword(request);
	}
	
	@PostMapping("/status")
	public ResponseEntity<ResponseGetUserDTO> getUser(@RequestBody RequestGetUserDTO request){
		return authenticationService.getUser(request);
	}
	
}
