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
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Entity
public class Orders extends Timestamped {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurants;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resFood_id")
    private ResFood resFoods;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private Long restaurantIds;
    @Column(nullable = false)
    private String restaurantName;
    @Column(nullable = false)
    private Long foodid;
    @Column(nullable = false)
    private String foodName;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int deliveryFee;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt = LocalDateTime.now();

        public Orders(OrdersRequestDto params){
        this.restaurantIds = params.getResId();
        this.restaurantName = params.getRestaurantName();
        this.foodid = params.getFoodid();
        this.foodName = params.getFoodName();
        this.quantity = params.getQuantity();
        this.price = params.getPrice();
        this.deliveryFee = params.getDeliveryFee();
        this.modifiedAt = params.getModifiedAt();
    }

    public void update(OrdersRequestDto params){

    }
}
