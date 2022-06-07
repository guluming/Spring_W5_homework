package com.sparta.spring_w5_homework.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FoodOrderResponseDto {
    private String name;
    private int quantity;
    private int price;
}
