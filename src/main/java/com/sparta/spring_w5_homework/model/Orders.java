package com.sparta.spring_w5_homework.model;

import com.sparta.spring_w5_homework.requestdto.OrdersRequestDto;
import com.sparta.spring_w5_homework.utils.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Entity
public class Orders {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String restaurantName;
    @OneToMany(mappedBy = "orders")
    private List<OrderFood> foods;
    @Column(nullable = false)
    private int deliveryFee;
    @Column(nullable = false)
    private int totalPrice;
//    @CreatedDate
//    private LocalDateTime createdAt;
//    @LastModifiedDate
//    private LocalDateTime modifiedAt = LocalDateTime.now();

    public Orders(OrdersRequestDto params) {
        this.restaurantName = params.getRestaurantName();
        this.foods = params.getFoods();
        this.deliveryFee = params.getDeliveryFee();
        this.totalPrice = params.getTotalPrice();
//        this.modifiedAt = params.getModifiedAt();
    }

    public void update(OrdersRequestDto params) {

    }
}
