package com.spring.jwt.controller;
import com.spring.jwt.Interfaces.IGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@Component
public class GameTimeController {
    @Autowired
    private IGame iGame;
    private static final long GAME_DURATION_SECONDS = 150; // 2 minutes and 30 seconds

    private Instant gameStartTime = Instant.now();



    @Scheduled(fixedDelay = 1000 * 150)
    public ResponseEntity<Long> getRemainingTime() {

        Instant now = Instant.now();
        Duration remainingTime = Duration.between(now, gameStartTime.plusSeconds(GAME_DURATION_SECONDS));
        if (remainingTime.isNegative()) {
            // Game period is over, reset the game start time
            gameStartTime = Instant.now();
            remainingTime = Duration.ofSeconds(GAME_DURATION_SECONDS);
        }
        System.out.println("API inside "+remainingTime.getSeconds());

        return ResponseEntity.ok(remainingTime.getSeconds());
    }
    @Scheduled(fixedDelay = 15000)
    public void getRemainingTimeHit() {
        Instant now = Instant.now();
        Duration remainingTime = Duration.between(now, gameStartTime.plusSeconds(GAME_DURATION_SECONDS));
        if (remainingTime.isNegative()) {
            // Game period is over, reset the game start time
            gameStartTime = Instant.now();
            remainingTime = Duration.ofSeconds(GAME_DURATION_SECONDS);
        }
        if (remainingTime.getSeconds() <= 15) {
            System.out.println("inside sheduler"+remainingTime.getSeconds());
            getRemainingTime();
            iGame.makeWinNumber();

        }
        System.out.println(remainingTime.getSeconds());



    }
}