package com.spring.jwt.Interfaces;

import com.spring.jwt.entity.User;

public interface IGame {
    public String saveGameColorOrNumber(String referenceId, String colorOrNumber, Integer amount, String period);

    String updateChartTrend(Integer wonNumber);

    Object getLivePeriodNo();

    String saveChartTrend();

    String makeWinNumber();
}





