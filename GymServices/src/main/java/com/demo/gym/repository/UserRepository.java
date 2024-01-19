package com.demo.gym.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.gym.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query(value = "select * from gymuser where email  = :email",nativeQuery = true)
	User findEmail(@Param("email") String email);
	
	Optional<User> findByEmail(String email);


}
