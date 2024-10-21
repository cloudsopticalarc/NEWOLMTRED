package com.spring.jwt.repository;

import com.spring.jwt.entity.Profit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfitRepo extends JpaRepository<Profit,Integer> {
}
