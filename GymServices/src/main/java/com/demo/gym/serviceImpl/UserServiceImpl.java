package com.demo.gym.serviceImpl;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.gym.DTO.RequestForgotPasswordDTO;
import com.demo.gym.DTO.RequestUpdateInfoDTO;
import com.demo.gym.DTO.ResponseForgotPasswordDTO;
import com.demo.gym.DTO.ResponseUpdateInfoDTO;
import com.demo.gym.domain.User;
import com.demo.gym.repository.UserRepository;
import com.demo.gym.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<ResponseUpdateInfoDTO> updateInfo(RequestUpdateInfoDTO request) {
		String key = request.getCardNumber();
		try {
			User data = userRepository.findByEmail(request.getEmail()).get();
			if (data != null) {
				Cipher cipher = Cipher.getInstance("AES");
				byte[] keyBytes = Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8), 16);
				SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

				cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

				byte[] encryptedCardNumber = cipher.doFinal(request.getCardNumber().getBytes());
				byte[] encryptedCVV = cipher.doFinal(request.getCVV().getBytes());
				byte[] encryptedExpDate = cipher.doFinal(request.getExpDate().getBytes());
				byte[] encryptednameCardHolder = cipher.doFinal(request.getNameCardHolder().getBytes());

				data.setName(request.getName());
				data.setCardNumber(encryptedCardNumber.toString());
				data.setCVV(encryptedCVV.toString());
				data.setExpDate(encryptedExpDate.toString());
				data.setNameCardHolder(encryptednameCardHolder.toString());
				userRepository.save(data);

				return new ResponseEntity<>(
						new ResponseUpdateInfoDTO(HttpStatus.OK.toString().substring(0, 3), "Update Success"),
						HttpStatus.OK);

			} else {
				return new ResponseEntity<>(
						new ResponseUpdateInfoDTO(HttpStatus.NOT_FOUND.toString().substring(0, 3), "Email Wrong"),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResponseUpdateInfoDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(0, 3),
							e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<ResponseForgotPasswordDTO> forgotpassword(RequestForgotPasswordDTO request) {
		try {

			User data = userRepository.findByEmail(request.getEmail()).get();
			String password = passwordEncoder.encode(request.getPassword());

			if (data != null) {
				data.setPassword(password);
				userRepository.save(data);
				return new ResponseEntity<>(new ResponseForgotPasswordDTO(HttpStatus.OK.toString().substring(0, 3),
						"Change Password Success"), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ResponseForgotPasswordDTO(HttpStatus.NOT_FOUND.toString().substring(0, 3),
						"Email not found"), HttpStatus.NOT_ACCEPTABLE);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseForgotPasswordDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(0, 3),
					e.getCause().getMessage()), HttpStatus.OK);
		}

	}

}
