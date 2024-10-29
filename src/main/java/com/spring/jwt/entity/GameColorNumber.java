package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "GameColorNumber")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameColorNumber {
    @Id
    @Column(name = "Game_Color_Number_Id", nullable = false, columnDefinition = "INTEGER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId", nullable = false, columnDefinition = "INTEGER")
    private Integer userId;

    @Column(name = "DateAndTime", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dateAndTime;
    @Column(name = "amount", nullable = false, columnDefinition = "INTEGER")
    private Integer amount;
    @Column(name = "Black", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean black;
    @Column(name = "Red", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean red;
    @Column(name = "Yellow", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean yellow;

    @Column(name = "Zero", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean zero;

    @Column(name = "One", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean one;

    @Column(name = "Two", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean two;
    @Column(name = "Three", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean three;
    @Column(name = "Four", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean four;
    @Column(name = "Five", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean five;
    @Column(name = "Six", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean six;
    @Column(name = "Seven", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean seven;
    @Column(name = "Eight", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean eight;
    @Column(name = "Nine", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean nine;
    @Column(name = "TYPE", nullable = false, columnDefinition = "VARCHAR(15)")
    private String type;
    @Column(name = "WonNumber", nullable = false, columnDefinition = "INTEGER")
    private Integer wonNumber;
    @Column(name = "Period", nullable = false, columnDefinition = "VARCHAR(15)")
    private String period;

    @Column(name = "User_Reference_Id", nullable = false, columnDefinition = "VARCHAR(15)")
    private String userReferenceId;

    @Column(name = "WinStatus", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean winStatus;


}
