package com.sparta.spring_w5_homework.controller;

import com.sparta.spring_w5_homework.requestdto.RestaurantRequestDto;
import com.sparta.spring_w5_homework.responsedto.RestaurantResponseDto;
import com.sparta.spring_w5_homework.service.RestaurantService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;


    //음식점 등록
    @PostMapping("/restaurant/register")
    public RestaurantResponseDto saveRestaurant(@RequestBody RestaurantRequestDto jsonDate){
        return restaurantService.restaurantSave(jsonDate);
    }

    //음식점 전체 조회
    @GetMapping("/restaurants")
    public List<RestaurantResponseDto> findAllRestaurant(){
        return restaurantService.restaurantFindAll();
    }
}
