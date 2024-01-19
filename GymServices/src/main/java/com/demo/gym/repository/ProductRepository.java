package com.demo.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.gym.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query(value = "select * from productmaster where product_name = :name",nativeQuery = true)
	Product findByName(@Param("name") String name);
	
}
