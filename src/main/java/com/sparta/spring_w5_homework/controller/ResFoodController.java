package com.sparta.spring_w5_homework.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spring_w5_homework.requestdto.ResFoodRequestDto;
import com.sparta.spring_w5_homework.requestdto.ResFoodRequestListDto;
import com.sparta.spring_w5_homework.responsedto.ResFoodResponseDto;
import com.sparta.spring_w5_homework.service.ResFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class ResFoodController {
    private final ResFoodService resFoodService;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public String save(@PathVariable Long restaurantId, @RequestParam("foods") String jsonList)
            throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<ResFoodRequestListDto> resFoodDtoList = objectMapper.readValue(jsonList, new TypeReference<List<ResFoodRequestListDto>>() {});
          return resFoodService.foodSave(restaurantId, resFoodDtoList);
    }

    //메뉴판 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<ResFoodResponseDto> findByMenu(@PathVariable Long restaurantId){
        return resFoodService.findByMenu(restaurantId);
    }
}
