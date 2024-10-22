package com.spring.jwt.repository;

import com.spring.jwt.entity.RechargeTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RechargeTransactionRepo extends JpaRepository<RechargeTransactions,Integer> {
    @Query("SELECT wt FROM RechargeTransactions wt WHERE wt.rechargeReceiverId = :rechargeReceiverId" )
    List<RechargeTransactions> findByReceiverID(@Param("rechargeReceiverId") String rechargeReceiverId);

    @Query("SELECT wt FROM RechargeTransactions wt WHERE wt.rechargeSenderId = :rechargeSenderId" )
    List<RechargeTransactions> findByAdminSenderID(@Param("rechargeSenderId") String rechargeSenderId);
}
