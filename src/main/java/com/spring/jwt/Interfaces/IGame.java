package com.spring.jwt.Interfaces;

import com.spring.jwt.entity.User;

public interface IGame {
    public String saveGameColorOrNumber(String referenceId, String colorOrNumber, Integer amount, String period);

    public String updateChartTrend(Integer wonNumber,Integer wonColor);

    Object getLivePeriodNo();

    String saveChartTrend();

    String makeWinNumber();

    Object getResult();
}





