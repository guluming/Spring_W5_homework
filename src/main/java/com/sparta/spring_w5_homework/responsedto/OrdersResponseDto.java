package com.sparta.spring_w5_homework.responsedto;

import com.sparta.spring_w5_homework.model.Orders;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrdersResponseDto {
    private String restaurantName;
    private String name;
    private int quantity;
    private int price;
    private int deliveryFee;
    private int totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public OrdersResponseDto(){
        this.restaurantName = getRestaurantName();
    }
}
