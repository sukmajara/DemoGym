package com.demo.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.gym.domain.GymClass;

@Repository
public interface ClassRepository extends JpaRepository<GymClass, Long>{

}
