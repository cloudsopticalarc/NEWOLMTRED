package com.spring.jwt.dto;

import lombok.Data;

@Data
public class ResponseSizeObjectDto {
    public Long size;
    public Object object;


    public ResponseSizeObjectDto(Long size, Object object) {
        this.size = size;
        this.object = object;
    }
}
