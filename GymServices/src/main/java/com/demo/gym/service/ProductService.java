package com.demo.gym.service;

import org.springframework.http.ResponseEntity;

import com.demo.gym.DTO.ResponseGetDataProductDTO;


public interface ProductService {

	public ResponseEntity<ResponseGetDataProductDTO> getDataProduct();

}
