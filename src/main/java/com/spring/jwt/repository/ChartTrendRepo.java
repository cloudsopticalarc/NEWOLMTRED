package com.spring.jwt.repository;

import com.spring.jwt.entity.ChartTrend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChartTrendRepo extends JpaRepository<ChartTrend,Integer> {
    List<ChartTrend> findByRunningStatus(String running);
    @Query("SELECT ct FROM ChartTrend ct WHERE ct.runningStatus = :runningStatus")
    List<ChartTrend> findByDoneStatus(@Param("runningStatus") String runningStatus);
//
//    @Query("SELECT ct FROM ChartTrend ct WHERE ct.runningStatus = :runningStatus AND ct.period = :period")
//    List<ChartTrend> findByRefAndPeriod(@Param("referanceId") String referanceId,
//                                        @Param("period") Long period,
//                                        @Param("runningStatus") String runningStatus);
}
