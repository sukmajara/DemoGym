package com.demo.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.gym.DTO.RequestForgotPasswordDTO;
import com.demo.gym.DTO.RequestUpdateInfoDTO;
import com.demo.gym.DTO.ResponseForgotPasswordDTO;
import com.demo.gym.DTO.ResponseUpdateInfoDTO;
import com.demo.gym.service.UserService;

@RestController
@RequestMapping("/gym/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/updateinfo")
	public ResponseEntity<ResponseUpdateInfoDTO> updateInfo(@RequestBody RequestUpdateInfoDTO request){
		return userService.updateInfo(request);
	}
	
	@PostMapping("/changepassword")
	public ResponseEntity<ResponseForgotPasswordDTO> forgotpassword(@RequestBody RequestForgotPasswordDTO request){
		return userService.forgotpassword(request);
	}
	
	

}
