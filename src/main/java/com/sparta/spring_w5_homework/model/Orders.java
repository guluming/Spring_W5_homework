package com.sparta.spring_w5_homework.model;

import com.sparta.spring_w5_homework.requestdto.OrdersRequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.List;


@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Entity
public class Orders {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany(mappedBy = "ordersId")
    private List<OrderFood> foods;
    @Column(nullable = false)
    private String restaurantName;
    @Column(nullable = false)
    private int deliveryFee;
    @Column(nullable = false)
    private int totalPrice;

    public Orders(OrdersRequestDto params) {
        this.restaurantName = params.getRestaurantName();
        this.deliveryFee = params.getDeliveryFee();
        this.totalPrice = params.getTotalPrice();
    }

    public void update(OrdersRequestDto params) {

    }
}
