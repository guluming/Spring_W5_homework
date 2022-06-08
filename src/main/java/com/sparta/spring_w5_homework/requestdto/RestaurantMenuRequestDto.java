package com.sparta.spring_w5_homework.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantMenuRequestDto {
    private String name;
    private int quantity;
    private int price;
}
