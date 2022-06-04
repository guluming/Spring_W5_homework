package com.sparta.spring_w5_homework.controller;

import com.sparta.spring_w5_homework.responsedto.OrdersResponseDto;
import com.sparta.spring_w5_homework.service.OrderFoodService;
import com.sparta.spring_w5_homework.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrdersController {
    private final OrdersService ordersService;
    private final OrderFoodService orderFoodService;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //주문
    @PostMapping("/order/request")
    public String save(@RequestParam Long restaurantId, @RequestParam(value = "foods[]")List<List<String>> foods){
        boolean ordersresult = ordersService.ordersSave(restaurantId, foods);
        boolean orderFoodresult = orderFoodService.orderFoodSave(restaurantId, foods);
        if(!ordersresult){
            return "음식점 ID를 확인해 주세요.";
        } else if (!orderFoodresult) {
            return "주문 음식을 확인해 주세요.";
        }
        return "주문이 완료 되었습니다.";
    }

    //주문 조회
//    @GetMapping("/orders")
//    public List<OrdersResponseDto> findAll(){
//        return ordersService.ordersFindAll();
//    }
}
