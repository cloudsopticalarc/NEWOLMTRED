package com.spring.jwt.dto;

import lombok.Data;

@Data
public class WithdrawRecponceDto {
    public String message;
    public Object localDateTime;
    public Object object;


    public WithdrawRecponceDto(String message, Object localDateTime, Object object) {
        this.message = message;
        this.object = object;
        this.localDateTime =  localDateTime;
    }
}
