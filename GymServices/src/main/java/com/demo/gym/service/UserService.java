package com.demo.gym.service;

import org.springframework.http.ResponseEntity;

import com.demo.gym.DTO.RequestForgotPasswordDTO;
import com.demo.gym.DTO.RequestUpdateInfoDTO;
import com.demo.gym.DTO.ResponseForgotPasswordDTO;
import com.demo.gym.DTO.ResponseUpdateInfoDTO;


public interface UserService {

	public ResponseEntity<ResponseUpdateInfoDTO> updateInfo(RequestUpdateInfoDTO request);
	public ResponseEntity<ResponseForgotPasswordDTO> forgotpassword( RequestForgotPasswordDTO request);


}
