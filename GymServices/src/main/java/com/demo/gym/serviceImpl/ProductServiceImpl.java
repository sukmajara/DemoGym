package com.demo.gym.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.gym.DTO.ResponseGetDataProductDTO;
import com.demo.gym.domain.Product;
import com.demo.gym.repository.ProductRepository;
import com.demo.gym.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public ResponseEntity<ResponseGetDataProductDTO> getDataProduct() {
		try {

			List<Product> data = productRepository.findAll();

			return new ResponseEntity<>(
					new ResponseGetDataProductDTO(HttpStatus.OK.toString().substring(0, 3), "SUCCESS", data),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResponseGetDataProductDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(0, 3),
							e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
