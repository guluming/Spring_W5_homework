package com.sparta.spring_w5_homework.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spring_w5_homework.model.Orders;
import com.sparta.spring_w5_homework.requestdto.OrderFoodsListDto;
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
    public OrdersResponseDto save(@RequestParam("restaurantId") Long restaurantId, @RequestParam("foods") String jsonList) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<OrderFoodsListDto> orderFoodsDtoList = objectMapper.readValue(jsonList, new TypeReference<List<OrderFoodsListDto>>() {});

        return ordersService.ordersSave(restaurantId ,orderFoodsDtoList);
    }

    //주문 조회
//    @GetMapping("/orders")
//    public List<OrdersResponseDto> findAll(){
//        return ordersService.ordersFindAll();
//    }
}
