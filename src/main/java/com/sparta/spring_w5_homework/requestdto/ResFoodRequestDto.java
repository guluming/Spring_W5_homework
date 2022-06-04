package com.sparta.spring_w5_homework.requestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
public class ResFoodRequestDto {
    private List<Foods> foods;

    public static class Foods{

        @JsonProperty("name")
        private String name;

        @JsonProperty("price")
        private int price;
    }

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
