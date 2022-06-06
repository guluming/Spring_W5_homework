package com.sparta.spring_w5_homework.requestdto;

import com.sparta.spring_w5_homework.model.OrderFood;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class OrdersRequestDto {
    private String restaurantName;
//    private List<OrderFood> foods;
//    private Long restaurantId;
    private int deliveryFee;
    private int totalPrice;

//    public static class OrderFood {
//        private String foodname;
//        private int quantity;
//        private int price;
//    }
//    @LastModifiedDate
//    private LocalDateTime modifiedAt;
}
