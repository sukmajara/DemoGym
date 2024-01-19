package com.demo.gym.repository;

import com.demo.gym.domain.TokenPayment;
import com.demo.gym.domain.TokenUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenPaymentRepository extends JpaRepository<TokenPayment, Long> {
    @Query(value = "Select * from tokenpayment where token = :tokenin",nativeQuery = true)
    Optional<TokenPayment> findByToken(@Param("tokenin") String token);

    @Query(value = "SELECT * FROM tokenpayment WHERE user_id = :userid AND confirm_at IS null order by exp_at desc limit 1",nativeQuery = true)
    Optional<TokenPayment> findNewestToken(@Param("userid") Long userid);
}
