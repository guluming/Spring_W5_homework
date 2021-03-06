package com.sparta.spring_w5_homework.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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


    //음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void saveResFood(@PathVariable Long restaurantId, @RequestBody List<ResFoodRequestListDto> jsonList){

//        ObjectMapper objectMapper = new ObjectMapper()
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        List<ResFoodRequestListDto> resFoodDtoList = objectMapper.convertValue(jsonList, new TypeReference<List<ResFoodRequestListDto>>() {});

        resFoodService.saveResFood(restaurantId, jsonList);
    }

    //메뉴판(특정 음식점 음식 전체) 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<ResFoodResponseDto> findByRestaurantMenu(@PathVariable Long restaurantId){
        return resFoodService.findByRestaurantMenu(restaurantId);
    }
}
