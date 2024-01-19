package com.demo.gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.gym.domain.GymClass;

@Repository
public interface GymClassRepository extends JpaRepository<GymClass, Long> {

	@Query(value = "select * from classmaster where user_id = :userId", nativeQuery = true)
	List<GymClass> findUserList(@Param("userId") Long userId);

	@Query(value = "select * from classmaster where user_id = :userId", nativeQuery = true)
	GymClass findUser(@Param("userId") Long userId);

	@Query(value = "select * from classmaster where user_id = :userId AND status = 'CANCELED' AND product_id = :productId", nativeQuery = true)
	GymClass findUserStatusCancel(@Param("userId") Long userId, @Param("productId") Long productId);
	
	@Query(value = "select * from classmaster where user_id = :userId AND status = 'ACTIVE' AND product_id = :productId", nativeQuery = true)
	GymClass findUserStatusActive(@Param("userId") Long userId, @Param("productId") Long productId);
}
