package com.demo.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.gym.DTO.RequestCancelClassDTO;
import com.demo.gym.DTO.RequestInfoUserClassDTO;
import com.demo.gym.DTO.RequestRegisterClassDTO;
import com.demo.gym.DTO.RequestUpdateClassDTO;
import com.demo.gym.DTO.ResponseCancelClassDTO;
import com.demo.gym.DTO.ResponseInfoUserClassDTO;
import com.demo.gym.DTO.ResponseRegisterClassDTO;
import com.demo.gym.DTO.ResponseUpdateClassDTO;
import com.demo.gym.service.GymClassService;

@RestController
@RequestMapping("/gym/class")
public class GymClassController {
	
	@Autowired
	GymClassService gymClassService;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseRegisterClassDTO> registerClass(@RequestBody RequestRegisterClassDTO request){
		return gymClassService.registerClass(request);
	}
	
	@PostMapping("/cancel")
	public ResponseEntity<ResponseCancelClassDTO> cancelClass(@RequestBody RequestCancelClassDTO request){
		return gymClassService.cancelClass(request);
	}
	
	@PostMapping("/update")
	public ResponseEntity<ResponseUpdateClassDTO> updateClass(@RequestBody RequestUpdateClassDTO request){
		return gymClassService.updateClass(request);
	}
	
	@PostMapping("/info")
	public ResponseEntity<ResponseInfoUserClassDTO> infoUserClass(@RequestBody RequestInfoUserClassDTO request){
		return gymClassService.infoUserClass(request);
	}
}
