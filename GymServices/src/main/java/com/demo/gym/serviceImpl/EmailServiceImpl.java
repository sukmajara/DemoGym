package com.demo.gym.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.demo.gym.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	JavaMailSender mailSender;
	
	public void sendemail(String toEmail, String subject, String body) {
		SimpleMailMessage mailMessage =  new SimpleMailMessage();
		mailMessage.setFrom("emailtestingdevelopment@gmail.com");
		mailMessage.setTo(toEmail);
		mailMessage.setText(body);
		mailMessage.setSubject(subject);
		
		mailSender.send(mailMessage);
	}
	
}
