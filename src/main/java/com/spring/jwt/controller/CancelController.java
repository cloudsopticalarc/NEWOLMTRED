package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.UserService;
import com.spring.jwt.dto.ResponseDto;
import com.spring.jwt.repository.ProfitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/UserAdminCancel")
public class CancelController {
    @Autowired
    private UserService userService;

    @PatchMapping("/cancelWithdraw")
    public ResponseEntity<?> withdrawApprovalUserSide(@RequestParam Integer withdrawID){
        try{
            String message = userService.cancelWithdraw(withdrawID);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success",message));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }

}
