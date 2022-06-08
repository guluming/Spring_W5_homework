package com.sparta.spring_w5_homework.requestdto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ResFoodRequestListDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private int price;
}
