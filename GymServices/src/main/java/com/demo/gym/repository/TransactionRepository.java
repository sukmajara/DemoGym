package com.demo.gym.repository;

import com.demo.gym.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM tokenpayment WHERE user_id = :userId AND status  = 0 AND product_id = 2",nativeQuery = true)
    Transaction findbyUserid(@Param("userId") Long id);

    Transaction findByStatus(Long id);
}
