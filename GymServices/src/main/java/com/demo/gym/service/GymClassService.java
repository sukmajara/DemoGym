package com.demo.gym.service;

import org.springframework.http.ResponseEntity;

import com.demo.gym.DTO.RequestCancelClassDTO;
import com.demo.gym.DTO.RequestInfoUserClassDTO;
import com.demo.gym.DTO.RequestRegisterClassDTO;
import com.demo.gym.DTO.RequestUpdateClassDTO;
import com.demo.gym.DTO.ResponseCancelClassDTO;
import com.demo.gym.DTO.ResponseInfoUserClassDTO;
import com.demo.gym.DTO.ResponseRegisterClassDTO;
import com.demo.gym.DTO.ResponseUpdateClassDTO;

public interface GymClassService {
	public ResponseEntity<ResponseRegisterClassDTO> registerClass(RequestRegisterClassDTO reqeust);

	public ResponseEntity<ResponseCancelClassDTO> cancelClass(RequestCancelClassDTO request);

	public ResponseEntity<ResponseUpdateClassDTO> updateClass( RequestUpdateClassDTO request);
	
	public ResponseEntity<ResponseInfoUserClassDTO> infoUserClass( RequestInfoUserClassDTO request);


}
