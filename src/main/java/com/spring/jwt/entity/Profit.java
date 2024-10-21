package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "profit")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profit {
    @Id
    @Column(name = "id", nullable = false, columnDefinition = "INTEGER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TransactionsDateAndTime", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime TransactionsDateAndTime;

    @Column(name = "rechargeSenderId", nullable = false, columnDefinition = "VARCHAR(255)")
    private String rechargeSenderId;

    @Column(name = "receiverId", nullable = false, columnDefinition = "VARCHAR(255)")
    private String receiverId;

    @Column(name = "transactionsStatus", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean transactionsStatus;

    @Column(name = "transactionFees", nullable = false, columnDefinition = "FLOAT")
    private Float transactionFees;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255)")
    private String status;

    @Column(name = "transactionAmount", nullable = false, columnDefinition = "FLOAT")
    private Float transactionAmount;

    @Column(name = "sourceOfProfit", nullable = false, columnDefinition = "VARCHAR(255)")
    private String sourceOfProfit;


}

