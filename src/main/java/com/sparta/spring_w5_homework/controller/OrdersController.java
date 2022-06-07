package com.sparta.spring_w5_homework.controller;

import com.sparta.spring_w5_homework.requestdto.OrderListDto;
import com.sparta.spring_w5_homework.responsedto.OrdersResponseDto;
import com.sparta.spring_w5_homework.service.OrdersService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrdersController {
    private final OrdersService ordersService;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //주문
    @PostMapping("/order/request")
    public OrdersResponseDto save(@RequestBody OrderListDto params) {
        return ordersService.ordersSave(params.getRestaurantId(), params.getFoods());
    }

    //주문 조회
    @GetMapping("/orders")
    public List<OrdersResponseDto> findAll() {
        return ordersService.ordersFindAll();
    }
}
