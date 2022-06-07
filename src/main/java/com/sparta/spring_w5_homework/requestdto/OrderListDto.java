package com.sparta.spring_w5_homework.requestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Map;

@NoArgsConstructor
@Getter
public class OrderListDto {
    @JsonProperty("restaurantId")
    private Long restaurantId;

    @JsonProperty("foods")
    private ArrayList<Map<String,Object>> foods;
}
