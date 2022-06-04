package com.sparta.spring_w5_homework.responsedto;

import com.sparta.spring_w5_homework.model.ResFood;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ResFoodResponseDto {
    private Long id;
    private String name;
    private int price;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ResFoodResponseDto(ResFood entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
    }
}
