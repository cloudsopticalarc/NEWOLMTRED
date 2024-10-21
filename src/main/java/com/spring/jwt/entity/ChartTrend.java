package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ChartTrend")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChartTrend {
    @Id
    @Column(name = "Chart_Trend_Id", nullable = false, columnDefinition = "INTEGER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "WonNumber", nullable = false, columnDefinition = "INTEGER")
    private Integer wonNumber;
    @Column(name = "Period", nullable = false, columnDefinition = "LONG")
    private Long period;
    @Column(name = "RunningStatus", nullable = false, columnDefinition = "VARCHAR(15)")
    private String runningStatus;



}
