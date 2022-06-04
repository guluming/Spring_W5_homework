package com.sparta.spring_w5_homework.model;

import com.sparta.spring_w5_homework.requestdto.RestaurantRequestDto;
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
public class Restaurant extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private int minOrderPrice;
    @Column(nullable = false)
    private int deliveryFee;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt = LocalDateTime.now();

    public Restaurant(RestaurantRequestDto params){
        this.name = params.getName();
        this.minOrderPrice = params.getMinOrderPrice();
        this.deliveryFee = params.getDeilveryFee();
        this.modifiedAt = params.getModifiedAt();
    }

    public void update(RestaurantRequestDto params){

    }
}