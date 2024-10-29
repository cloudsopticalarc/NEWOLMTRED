package com.spring.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping("/time")
public class timeController {
    @Autowired
    private GameTimeController gameTimeController;

    @GetMapping("/remaining-time")
    public ResponseEntity<Long> getTime() {
       Long remainingTime = gameTimeController.getRemainingTime().getBody();
   return ResponseEntity.ok(remainingTime);

    }
}
