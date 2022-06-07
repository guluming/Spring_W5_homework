package com.sparta.spring_w5_homework.model;

import com.sparta.spring_w5_homework.requestdto.ResFoodRequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Entity
public class ResFood{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private Long restaurantId;

    public ResFood(ResFoodRequestDto params){
        this.name = params.getName();
        this.price = params.getPrice();
        this.restaurantId = params.getRestaurantId();
    }

    public void update(ResFoodRequestDto params){

    }
}
