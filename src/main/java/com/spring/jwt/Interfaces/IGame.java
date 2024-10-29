package com.spring.jwt.Interfaces;

import com.spring.jwt.entity.ChartTrend;
import com.spring.jwt.entity.GameColorNumber;
import com.spring.jwt.entity.User;

import java.util.List;

public interface IGame {
    public String saveGameColorOrNumber(String referenceId, String colorOrNumber, Integer amount, String period);

    public String updateChartTrend(Integer wonNumber,Integer wonColor);

    ChartTrend getLivePeriodNo();

    String saveChartTrend();

    String makeWinNumber();

    Object getResult();

    List<GameColorNumber> getOrderByUserRefIdDone(String referanceId);
    List<GameColorNumber> getOrderByUserRefIdRunning(String referanceId);

}





