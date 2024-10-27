package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.UserService;
import com.spring.jwt.dto.ResponceDto;
import com.spring.jwt.dto.ResponseDto;
import com.spring.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;





@RestController
@RequestMapping("/superAdmin")
public class SuperAdminController {
    @Autowired
    private UserService userService;


    @GetMapping("/getProfit")
    public ResponseEntity<?> getByReferenceId(){
        try{
            Object user = userService.getProfit();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success",user));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }

}
