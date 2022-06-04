package com.sparta.spring_w5_homework.model;

import com.sparta.spring_w5_homework.requestdto.ResFoodRequestDto;
import com.sparta.spring_w5_homework.utils.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Entity
public class ResFood extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private Long restaurantId;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt = LocalDateTime.now();

    public ResFood(String name, int price, Long restaurantId, LocalDateTime modifiedAt){
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
        this.modifiedAt = modifiedAt;
    }

//    public ResFood(ResFoodRequestDto params, Long restaurantId){
//        this.name = params.getFoods().get(1)
//        this.price = params.getFoods();
//        this.modifiedAt = params.getModifiedAt();
//        this.restaurantId = restaurantId;
//    }

    public void update(ResFoodRequestDto params){

    }
}
