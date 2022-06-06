package com.sparta.spring_w5_homework.responsedto;

import com.sparta.spring_w5_homework.model.Restaurant;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private int minOrderPrice;
    private int deilveryFee;
//    private LocalDateTime createdAt;
//    private LocalDateTime modifiedAt;

    public RestaurantResponseDto(Restaurant entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.minOrderPrice = entity.getMinOrderPrice();
        this.deilveryFee = entity.getDeliveryFee();
//        this.createdAt = entity.getCreatedAt();
//        this.modifiedAt = entity.getModifiedAt();
    }
}
