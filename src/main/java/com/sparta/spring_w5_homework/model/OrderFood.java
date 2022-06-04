package com.sparta.spring_w5_homework.model;

import com.sparta.spring_w5_homework.requestdto.OrderFoodRequestDto;
import com.sparta.spring_w5_homework.utils.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderFood extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private Long ordersId;
    @Column(nullable = false)
    private Long foodId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private int price;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt = LocalDateTime.now();

    public OrderFood(Long ordersId, String name, int quantity, int price, LocalDateTime modifiedAt){
        this.ordersId = ordersId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.modifiedAt = modifiedAt;
    }

    public OrderFood(OrderFoodRequestDto params){
        this.ordersId = params.getOrdersId();;
//        this.foodId = params.getFoodId();
        this.name = params.getName();
        this.quantity = params.getQuantity();
        this.price = params.getPrice();
        this.modifiedAt = params.getModifiedAt();
    }
}
