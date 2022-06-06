package com.sparta.spring_w5_homework.responsedto;

import com.sparta.spring_w5_homework.model.OrderFood;
import com.sparta.spring_w5_homework.model.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class OrdersResponseDto {
    private String restaurantName;
    private List<FoodOrderResponseDto> foods;
//    private OrderFood foods;
//    private String foodname;
//    private int quantity;
//    private int price;
    private int deliveryFee;
    private int totalPrice;
//    private LocalDateTime createdAt;
//    private LocalDateTime modifiedAt;

//    public static class FoodOrderResponseDto {
//        private String foodname;
//        private int quantity;
//        private int price;
//    }

//    public OrdersResponseDto(Orders entity) {
//        this.restaurantName = entity.getRestaurantName();
////        this.foods = getFoods();
////        this.foodname = foods.getFoodName();
////        this.quantity = foods.getQuantity();
////        this.price = foods.getPrice();
//        this.deliveryFee = entity.getDeliveryFee();
//        this.totalPrice = entity.getTotalPrice();
//    }
}
