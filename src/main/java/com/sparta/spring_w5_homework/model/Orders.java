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

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Entity
public class Orders extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private Long restaurantId;
    @Column(nullable = false)
    private String restaurantName;
    @Column(nullable = false)
    private Long foodId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int deliveryFee;
    @Column(nullable = false)
    private int totalPrice;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt = LocalDateTime.now();

    public Orders(OrdersRequestDto params){
        this.restaurantId =params.getRestaurantId();
        this.restaurantName = params.getRestaurantName();
        this.foodId = params.getFoodId();
        this.name = params.getName();
        this.quantity = params.getQuantity();
        this.price = params.getPrice();
        this.deliveryFee = params.getDeliveryFee();
        this.totalPrice = params.getTotalPrice();
        this.modifiedAt = params.getModifiedAt();
    }

    public void update(OrdersRequestDto params){

    }
}
