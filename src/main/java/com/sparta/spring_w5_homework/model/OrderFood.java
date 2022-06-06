package com.sparta.spring_w5_homework.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class OrderFood {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders ordersId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "resfood_id")
//    private ResFood resFoodId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "restaurant_id")
//    private Restaurant restaurantId;

    @Column
    private String foodName;
    @Column
    private int quantity;
    @Column
    private int price;

    public OrderFood(Orders ordersId, String foodName, int quantity, int price){
        this.ordersId = ordersId;
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
    }

//    public OrderFood(String foodName, int quantity, int price){
//        this.foodName = foodName;
//        this.quantity = quantity;
//        this.price = price;
//    }
}
