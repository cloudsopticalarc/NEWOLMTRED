package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "WinNumber")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinNumber {
    @Id
    @Column(name = "Win_Number_Id", nullable = false, columnDefinition = "INTEGER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Win_Number", nullable = false, columnDefinition = "INTEGER")
    private Integer winNumber;

}
