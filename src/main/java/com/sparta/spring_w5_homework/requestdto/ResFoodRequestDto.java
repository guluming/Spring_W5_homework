package com.sparta.spring_w5_homework.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResFoodRequestDto {
    private String name;
    private int price;
    private Long restaurantId;
}
