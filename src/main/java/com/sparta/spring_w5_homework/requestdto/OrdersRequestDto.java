package com.sparta.spring_w5_homework.requestdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class OrdersRequestDto {
    private Long resId;
    private String restaurantName;
    private int deliveryFee;
    private int totalPrice;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
