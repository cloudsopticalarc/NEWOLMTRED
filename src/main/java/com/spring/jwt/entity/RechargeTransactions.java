package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "RechargeTransactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RechargeTransactions {
    @Id
    @GeneratedValue
    private Integer RechargeTransactions_id;
    @Column(name = "RechargeTransactionsDateAndTime", nullable = false)
    private LocalDateTime rechargeTransactionsDateAndTime;
    @Column(name = "RechargeSenderId", nullable = false)
    private String rechargeSenderId;
    @Column(name = "RechargeReceiverId", nullable = false)
    private String rechargeReceiverId;
    @Column(name = "RechargeTransactionsStatus", nullable = false)
    private Boolean rechargeTransactionsStatus;
    @Column(name = "TransactionFees", nullable = false)
    private Float transactionFees;

    @Column(name = "TransactionAmount", nullable = false)
    private Float transactionAmount;

}
