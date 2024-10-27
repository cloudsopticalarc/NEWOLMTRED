package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.UserService;
import com.spring.jwt.dto.ResponceDto;
import com.spring.jwt.dto.ResponseDto;
import com.spring.jwt.entity.User;
import com.spring.jwt.entity.WithdrawTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping("/index")
    public ResponseEntity<String> index(Principal principal){
        return ResponseEntity.ok("Welcome to admin page : " + principal.getName());


    }

    @GetMapping("/getByReferenceId")
    public ResponseEntity<?> getByReferenceId(@RequestParam String referenceId){
        try{
            User user = userService.getByReferenceIdAdminRecharge(referenceId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success",user));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }
    @PatchMapping("/recharge")
    public ResponseEntity<?> recharge(@RequestParam String postUserId,@RequestParam String getUserId,@RequestParam Float amount){
        try{
            String message = userService.recharge(postUserId,getUserId,amount);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success",message));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }
    @PatchMapping("/withdrawForAdminside")
    public ResponseEntity<?> withdraw(@RequestParam String postUserId,@RequestParam String getUserId,@RequestParam Float amount){
        try{
            Object message = userService.withdraw(postUserId,getUserId,amount);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success",message));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }

    @GetMapping("/userNotificationList")
    public ResponseEntity<?> userNotificationList(@RequestParam String getUserId ){
        try{
            Object withdrawTransaction  = userService.userNotificationList(getUserId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success",withdrawTransaction));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }
    @GetMapping("/withdrawApprovalUserSide")
        public ResponseEntity<?> withdrawApprovalUserSide(@RequestParam Integer withdrawID){
        try{
            String message = userService.withdrawApprovalUserSide(withdrawID);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success",message));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }
    @GetMapping("/getStatusAfterWithdawProcced")
    public ResponseEntity<?> getStatusAfterWithdawProcced(@RequestParam String User_R_Id, @RequestParam String transactionIdGenerator){
        try{
            Boolean response = userService.getStatusAfterWithdawProcced(User_R_Id,transactionIdGenerator);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success",response));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }

}
