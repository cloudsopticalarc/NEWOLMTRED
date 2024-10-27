package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.IGame;
import com.spring.jwt.Interfaces.UserService;
import com.spring.jwt.dto.ResponceDto;
import com.spring.jwt.dto.ResponseDto;
import com.spring.jwt.entity.ChartTrend;
import com.spring.jwt.entity.GameColorNumber;
import com.spring.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userGame")
public class GameController {
    @Autowired
    private IGame iGame;
    @Autowired
    private UserService userService;

    @GetMapping("/getByUserId")
    public ResponseEntity<?> getByUserId(@RequestParam Integer userID) {
        try {
            User user = userService.getByUserId(userID);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", user));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess", e.getMessage()));
        }
    }

    @PostMapping("/saveGameColorOrNumber")
    public ResponseEntity<?> saveGameColorOrNumber(@RequestParam String referenceId, @RequestParam String colorOrNumber, @RequestParam Integer amount, @RequestParam String period) {
        try {
            String message = iGame.saveGameColorOrNumber(referenceId, colorOrNumber, amount, period);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success", message));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess", e.getMessage()));
        }
    }

    @PostMapping("/saveChartTrend")
    public ResponseEntity<?> saveChartTrend() {
        try {
            String message = iGame.saveChartTrend();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success", message));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess", e.getMessage()));
        }
    }

    @PatchMapping("/updateChartTrend")
    public ResponseEntity<?> updateChartTrend(@RequestParam Integer wonNumber,@RequestParam Integer wonColor) {
        try {
            String message = iGame.updateChartTrend(wonNumber,wonColor);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success", message));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess", e.getMessage()));
        }
    }

    @GetMapping("/getLivePeriodNo")
    public ResponseEntity<?> getLivePeriodNo() {
        try {
            Object chartTrend = iGame.getLivePeriodNo();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", chartTrend));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess", e.getMessage()));
        }
    }
    @GetMapping("/getResult")
    public ResponseEntity<?> getResult() {
        try {
            Object chartTrend = iGame.getResult();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", chartTrend));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess", e.getMessage()));
        }
    }
    @GetMapping("/getOrderByUserRefIdDone")
    public ResponseEntity<?> getOrderByUserRefIdDone(@RequestParam String referanceId) {
        try {
            List<GameColorNumber> chartTrend = iGame.getOrderByUserRefIdDone(referanceId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", chartTrend));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess", e.getMessage()));
        }
    }
    @GetMapping("/getOrderByUserRefIdRunning")
    public ResponseEntity<?> getOrderByUserRefIdRunning(@RequestParam String referanceId) {
        try {
            List<GameColorNumber> chartTrend = iGame.getOrderByUserRefIdRunning(referanceId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", chartTrend));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess", e.getMessage()));
        }
    }
    @GetMapping("/getWithdraw")
    public ResponseEntity<?> getWithdraw(@RequestParam String referanceId) {
        try {
            Object chartTrend = userService.getWithdraw(referanceId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", chartTrend));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess", e.getMessage()));
        }
    }
    @GetMapping("/getRechargeAdminside")
    public ResponseEntity<?> getRechargeAdminside(@RequestParam String referanceId) {
        try {
            Object chartTrend = userService.getRechargeAdminSide(referanceId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", chartTrend));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess", e.getMessage()));
        }
    }  @GetMapping("/getWithdrawAdminSide")
    public ResponseEntity<?> getWithdrawAdminSide(@RequestParam String referanceId) {
        try {
            Object chartTrend = userService.getWithdrawAdmin(referanceId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", chartTrend));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess", e.getMessage()));
        }
    }
    @GetMapping("/getRecharge")
    public ResponseEntity<?> getRecharge(@RequestParam String referanceId) {
        try {
            Object chartTrend = userService.getRecharge(referanceId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", chartTrend));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess", e.getMessage()));
        }
    }
}

