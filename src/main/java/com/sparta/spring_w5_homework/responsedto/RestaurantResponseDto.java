package com.sparta.spring_w5_homework.responsedto;

import com.sparta.spring_w5_homework.model.Restaurant;

import lombok.Getter;

@Getter
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private int minOrderPrice;
    private int deliveryFee;

    public RestaurantResponseDto(Restaurant entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.minOrderPrice = entity.getMinOrderPrice();
        this.deliveryFee = entity.getDeliveryFee();
    }
}
