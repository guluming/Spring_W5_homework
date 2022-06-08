package com.sparta.spring_w5_homework.model;

import com.sparta.spring_w5_homework.requestdto.RestaurantRequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Entity
public class Restaurant{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private int minOrderPrice;
    @Column(nullable = false)
    private int deliveryFee;

    public Restaurant(RestaurantRequestDto params){
        this.name = params.getName();
        this.minOrderPrice = params.getMinOrderPrice();
        this.deliveryFee = params.getDeliveryFee();
    }
}
