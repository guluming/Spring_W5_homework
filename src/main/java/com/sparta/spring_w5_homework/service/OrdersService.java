package com.sparta.spring_w5_homework.service;

import com.sparta.spring_w5_homework.model.OrderFood;
import com.sparta.spring_w5_homework.model.Orders;
import com.sparta.spring_w5_homework.model.Restaurant;

import com.sparta.spring_w5_homework.repository.OrderFoodRepository;
import com.sparta.spring_w5_homework.repository.OrdersRepository;
import com.sparta.spring_w5_homework.repository.RestaurantRepository;

import com.sparta.spring_w5_homework.requestdto.OrdersRequestDto;
import com.sparta.spring_w5_homework.requestdto.RestaurantMenuRequestDto;

import com.sparta.spring_w5_homework.responsedto.OrdersResponseDto;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrderFoodService orderFoodService;
    private final OrdersRepository ordersRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderFoodRepository orderFoodRepository;


    //주문 정보 등록
    @Transactional
    public OrdersResponseDto saveOrder(Long restaurantId, ArrayList<Map<String, Object>> orderFoods) {

        //주문음식점 확인
        if (restaurantId == 0) {
            throw new IllegalArgumentException("해당 음식점을 찾을 수 없습니다.");
        }
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NullPointerException("음식점이 존재 하지 않습니다."));

        int totalPrice = 0;

        //주문 정보 등록
        for (Map<String, Object> orderFoodInfo : orderFoods) {

            //주문음식을 메뉴판에서 검색 함수 호출
            RestaurantMenuRequestDto orderFoodMenu = orderFoodService.searchRestaurantMenu(restaurantId, orderFoodInfo);

            totalPrice += orderFoodMenu.getPrice();
        }
        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("최소 주문 가격을 확인해 주세요.");
        }
        totalPrice = totalPrice + restaurant.getDeliveryFee();

        OrdersRequestDto params = new OrdersRequestDto(restaurant.getName(), restaurant.getDeliveryFee(), totalPrice);
        Orders orders = new Orders(params);
        Orders ordersId = ordersRepository.save(orders);

        //주문음식 정보 등록 함수 호출
        List<OrderFood> orderFoodList = orderFoodService.saveOrderFood(restaurantId, orderFoods, ordersId);

        //주문음식 정보 조회 함수 호출
        return new OrdersResponseDto(ordersId.getRestaurantName(), orderFoodService.findOrderFood(orderFoodList),
                ordersId.getDeliveryFee(), ordersId.getTotalPrice());
    }

    //주문 정보 전체 조회
    public List<OrdersResponseDto> findAllOrder() {
//        Sort sort = Sort.by(Sort.Direction.ASC, "id");
//        List<Orders> orderList = ordersRepository.findAll(sort);
        List<OrdersResponseDto> ordersResponseDtos = new ArrayList<>();
        List<Orders> orderList = ordersRepository.findAllByOrderByIdAsc();
        for (Orders orders : orderList) {
            List<OrderFood> orderFoodList = orderFoodRepository.findAllByOrdersId(orders);

            OrdersResponseDto ordersResponseDto = new OrdersResponseDto(orders.getRestaurantName(),
                    orderFoodService.findOrderFood(orderFoodList), orders.getDeliveryFee(), orders.getTotalPrice());

            ordersResponseDtos.add(ordersResponseDto);
        }
        return ordersResponseDtos;
    }
}
