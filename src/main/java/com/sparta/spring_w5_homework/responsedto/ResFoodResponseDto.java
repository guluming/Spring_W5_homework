package com.sparta.spring_w5_homework.responsedto;

import com.sparta.spring_w5_homework.model.ResFood;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ResFoodResponseDto {
    private Long id;
    private String name;
    private int price;

    public ResFoodResponseDto(ResFood entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
    }
}
