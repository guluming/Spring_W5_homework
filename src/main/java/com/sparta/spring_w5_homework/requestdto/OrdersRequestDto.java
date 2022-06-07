package com.sparta.spring_w5_homework.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrdersRequestDto {
    private String restaurantName;
    private int deliveryFee;
    private int totalPrice;
}
