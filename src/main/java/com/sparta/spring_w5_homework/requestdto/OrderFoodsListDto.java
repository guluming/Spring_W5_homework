package com.sparta.spring_w5_homework.requestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderFoodsListDto {
    @JsonProperty("id")
    private Long foodId;
    @JsonProperty("quantity")
    private int quantity;
}
