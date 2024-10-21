package com.spring.jwt.repository;

import com.spring.jwt.entity.ChartTrend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChartTrendRepo extends JpaRepository<ChartTrend,Integer> {
    Optional<ChartTrend> findByRunningStatus(String running);
}
