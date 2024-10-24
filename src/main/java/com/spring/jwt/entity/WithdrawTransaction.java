package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "WithdrawTransaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawTransaction {

    @Id
    @Column(name = "WithdrawTransaction_id", nullable = false, columnDefinition = "INTEGER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer WithdrawTransaction_id;

    @Column(name = "WithdrawTransactionsDateAndTime", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime withdrawTransactionsDateAndTime;

    @Column(name = "WithdrawSenderId", nullable = false, columnDefinition = "VARCHAR(255)")
    private String rechargeSenderId;

    @Column(name = "WithdrawReceiverId", nullable = false, columnDefinition = "VARCHAR(255)")
    private String withdrawReceiverId;

    @Column(name = "WithdrawTransactionsStatus", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean withdrawTransactionsStatus;

    @Column(name = "TransactionFees", nullable = false, columnDefinition = "FLOAT")
    private Float transactionFees;

    @Column(name = "withdrowStatus", nullable = false, columnDefinition = "VARCHAR(255)")
    private String withdrowStatus;

    @Column(name = "TransactionIdGenerator ", nullable = false, columnDefinition = "VARCHAR(255)")
    private String transactionIdGenerator ;

    @Column(name = "TransactionAmount", nullable = false, columnDefinition = "FLOAT")
    private Float transactionAmount;
}