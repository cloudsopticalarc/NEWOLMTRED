package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.IGame;
import com.spring.jwt.Interfaces.UserService;
import com.spring.jwt.dto.ResponceDto;
import com.spring.jwt.dto.ResponseDto;
import com.spring.jwt.entity.GameColorNumber;
import com.spring.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userGame")
public class GameController {
    @Autowired
    private IGame iGame;
    @Autowired
    private UserService userService;

    @GetMapping("/getByUserId")
    public ResponseEntity<?> getByUserId(@RequestParam Integer userID){
        try{
            User user = userService.getByUserId(userID);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success",user));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }
    @PostMapping("/saveGameColorOrNumber")
    public ResponseEntity<?> saveGameColorOrNumber(@RequestParam String referenceId,@RequestParam String colorOrNumber,@RequestParam Integer amount,@RequestParam String period){
        try{
            String message = iGame.saveGameColorOrNumber(referenceId,colorOrNumber,amount,period);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success",message));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }
    @PostMapping("/saveChartTrend")
    public ResponseEntity<?> saveChartTrend(){
        try{
            String message = iGame.saveChartTrend();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success",message));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }

    @PatchMapping("/updateChartTrend")
    public ResponseEntity<?> updateChartTrend(@RequestParam Integer wonNumber){
        try{
            String message = iGame.updateChartTrend(wonNumber);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success",message));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }
    @PatchMapping("/getLivePeriodNo")
    public ResponseEntity<?> getLivePeriodNo(){
        try{
            Object chartTrend = iGame.getLivePeriodNo();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success",chartTrend));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }
}

