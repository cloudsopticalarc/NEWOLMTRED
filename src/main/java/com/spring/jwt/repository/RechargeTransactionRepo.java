package com.spring.jwt.repository;

import com.spring.jwt.entity.RechargeTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RechargeTransactionRepo extends JpaRepository<RechargeTransactions,Integer> {
}
