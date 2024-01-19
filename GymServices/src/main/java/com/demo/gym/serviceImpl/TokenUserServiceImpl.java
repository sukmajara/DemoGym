package com.demo.gym.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.gym.domain.TokenUser;
import com.demo.gym.repository.TokenUserRepository;
import com.demo.gym.service.TokenUserService;

@Service
public class TokenUserServiceImpl implements TokenUserService{
	
	@Autowired
	private TokenUserRepository tokenUserRepository;
	
	public void saveConfirmationToken(TokenUser tokenUser) {
		tokenUserRepository.save(tokenUser);
	}
}
