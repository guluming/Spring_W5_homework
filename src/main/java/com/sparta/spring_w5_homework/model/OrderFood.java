package com.sparta.spring_w5_homework.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
//@AllArgsConstructor
@Setter
@Getter
@Entity
public class OrderFood {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders;

    @Column
    private String foodName;

    @Column
    private int quantity;

    @Column
    private int price;

//    @Builder
//    public OrderFood(){
//        this.foodName = getFoodName();
//        this.quantity = getQuantity();
//        this.price = getPrice();
//    }

}
