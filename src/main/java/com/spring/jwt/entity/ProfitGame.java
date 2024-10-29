package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "ProfitGame")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfitGame {
    @Id
    @Column(name = "id", nullable = false, columnDefinition = "INTEGER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TransactionsDateAndTime", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime TransactionsDateAndTime;

    @Column(name = "totalAmountColor", nullable = false, columnDefinition = "INTEGER")
    private Integer totalAmountColor;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(10)")
    private String status;
    @Column(name = "Period", nullable = false, columnDefinition = "VARCHAR(20)")
    private String period;

    @Column(name = "profitAmountColor", nullable = false, columnDefinition = "INTEGER")
    private Integer profitAmountColor;
    @Column(name = "profitAmountNumber", nullable = false, columnDefinition = "INTEGER")
    private Integer profitAmountNumber;
    @Column(name = "totalAmountNumber", nullable = false, columnDefinition = "INTEGER")
    private Integer totalAmountNumber;

    @Column(name = "sourceOfProfit", nullable = false, columnDefinition = "VARCHAR(10)")
    private String sourceOfProfit;


}

