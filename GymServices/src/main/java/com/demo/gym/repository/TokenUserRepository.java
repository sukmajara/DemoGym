package com.demo.gym.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.gym.domain.TokenUser;

@Repository
public interface TokenUserRepository extends JpaRepository<TokenUser, Long>{

	@Query(value = "Select * from tokenuser where token = :tokenin",nativeQuery = true)
	Optional<TokenUser> findByToken(@Param("tokenin") String token);
	
	@Query(value = "Select * from tokenuser where tokenuser.user_id= :userid",nativeQuery = true)
	Optional<TokenUser> FindTokenByUserid(@Param("userid") Long userid);
	@Query(value = "SELECT * FROM public.tokenuser WHERE user_id = :userid AND confirm_at IS null order by exp_at desc limit 1",nativeQuery = true)
	Optional<TokenUser> FindNewestToken(@Param("userid") Long userid);

}
