package com.demo.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.gym.DTO.ResponseGetDataProductDTO;
import com.demo.gym.service.ProductService;

@RestController
@RequestMapping("/gym/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/list")
	public ResponseEntity<ResponseGetDataProductDTO> getDataProduct(){
		return productService.getDataProduct();
	} 
}
