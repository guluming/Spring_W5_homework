package com.sparta.spring_w5_homework.requestdto;

import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
public class OrderFoodRequestDto {
    private Long ordersId;
//    private Long foodId;
    private String name;
    private int quantity;
    private int price;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
