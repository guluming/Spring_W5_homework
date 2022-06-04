package com.sparta.spring_w5_homework.controller;

import com.sparta.spring_w5_homework.requestdto.ResFoodRequestDto;
import com.sparta.spring_w5_homework.responsedto.ResFoodResponseDto;
import com.sparta.spring_w5_homework.service.ResFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ResFoodController {
    private final ResFoodService resFoodService;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public String save(@PathVariable Long restaurantId, @RequestBody ResFoodRequestDto foods){
          return resFoodService.foodSave(restaurantId, foods);
//        return "잘 받았습니다.";
    }

    //메뉴판 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<ResFoodResponseDto> findByMenu(@PathVariable Long restaurantId){
        return resFoodService.findByMenu(restaurantId);
    }
}
