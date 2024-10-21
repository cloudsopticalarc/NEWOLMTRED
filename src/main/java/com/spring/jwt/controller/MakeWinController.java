package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.IGame;
import com.spring.jwt.dto.ResponceDto;
import com.spring.jwt.dto.ResponseDto;
import com.spring.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/makeWinNumber")
public class MakeWinController {
    @Autowired
    private IGame iGame;
    @PatchMapping("/make")
    public ResponseEntity<?> makeWinNumber(){
        try{
            String message = iGame.makeWinNumber();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success",message));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }
}
