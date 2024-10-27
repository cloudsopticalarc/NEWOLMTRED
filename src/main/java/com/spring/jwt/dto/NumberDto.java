package com.spring.jwt.dto;

import lombok.Data;

@Data
public class NumberDto {
    public Integer key;
    public Integer value;

    public NumberDto(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "NumberDto{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

    public NumberDto() {

    }
}
