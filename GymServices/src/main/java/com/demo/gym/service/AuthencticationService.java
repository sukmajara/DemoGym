package com.demo.gym.service;

import org.springframework.http.ResponseEntity;

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

public interface AuthencticationService {

	public ResponseRegisterUserDTO registerUser(RequestRegisterUserDTO registerUser);

	public ResponseLoginUserDTO loginUser(RequestLoginUserDTO loginRequest);

	public ResponseEntity<ResponseGetUserDTO> getUser(RequestGetUserDTO request);

	public ResponseEntity<ResponseConfimTokenDTO> confirmToken(RequestConfirmTokenDTO request);

	public ResponseEntity<ResponseResendTokenDTO> resendToken(RequestResendTokenDTO request);
	
	public ResponseEntity<ResponseResetPasswordDTO> resetPassword(RequestResetPasswordDTO request);


}
