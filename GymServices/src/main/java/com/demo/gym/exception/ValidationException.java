package com.demo.gym.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.gym.DTO.ResponseErrorDTO;

@ControllerAdvice
public class ValidationException {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseErrorDTO> handleValidationException(MethodArgumentNotValidException ex){
		return new ResponseEntity<>(new ResponseErrorDTO(HttpStatus.BAD_REQUEST.toString().substring(0,3), ex.getBindingResult().getFieldError().getDefaultMessage())
				,HttpStatus.BAD_REQUEST);
	}
}
