package com.sparta.spring_w5_homework.model;

import lombok.*;

import javax.persistence.*;

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
    @Column
    private String name;
    @Column
    private int quantity;
    @Column
    private int price;

    public OrderFood(Orders ordersId, String name, int quantity, int price){
        this.ordersId = ordersId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
