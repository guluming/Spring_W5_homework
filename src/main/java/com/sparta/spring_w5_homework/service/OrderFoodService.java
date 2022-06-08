package com.sparta.spring_w5_homework.service;

import com.sparta.spring_w5_homework.model.OrderFood;
import com.sparta.spring_w5_homework.model.Orders;
import com.sparta.spring_w5_homework.model.ResFood;

import com.sparta.spring_w5_homework.repository.OrderFoodRepository;
import com.sparta.spring_w5_homework.repository.ResFoodRepository;
import com.sparta.spring_w5_homework.requestdto.RestaurantMenuRequestDto;
import com.sparta.spring_w5_homework.responsedto.FoodOrderResponseDto;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class OrderFoodService {
    private final OrderFoodRepository orderFoodRepository;
    private final ResFoodRepository resFoodRepository;


    //주문음식 정보 등록
    public List<OrderFood> saveOrderFood(Long restaurantId, ArrayList<Map<String, Object>> orderFoods, Orders ordersId) {
        List<OrderFood> orderFoodList = new ArrayList<>();
        for (Map<String, Object> orderFoodInfo : orderFoods) {

            //주문음식을 메뉴판에서 검색 함수 호출
            RestaurantMenuRequestDto orderFoodMenu = searchRestaurantMenu(restaurantId, orderFoodInfo);

            OrderFood orderFood = new OrderFood(ordersId, orderFoodMenu.getName(), orderFoodMenu.getQuantity(), orderFoodMenu.getPrice());
            orderFoodList.add(orderFood);
        }
        return orderFoodRepository.saveAll(orderFoodList);
    }

    //주문음식을 메뉴판에서 검색
    public RestaurantMenuRequestDto searchRestaurantMenu(Long restaurantId, Map<String, Object> orderFoodInfo) {
        Long foodId = ((Number) orderFoodInfo.get("id")).longValue();
        int quantity = (Integer) orderFoodInfo.get("quantity");
        int price;

        ResFood orderResFood = resFoodRepository.findByRestaurantIdAndId(restaurantId, foodId).orElseThrow(
                () -> new NullPointerException("해당 음식은 해당 음식점에 없습니다."));

        if (1 > quantity || quantity > 100) {
            throw new IllegalArgumentException("주문 가능한 음식 수량은 1개 ~ 100개 입니다.");
        } else {
            price = quantity * orderResFood.getPrice();
        }
        return new RestaurantMenuRequestDto(orderResFood.getName(), quantity, price);
    }

    //주문음식 정보 조회
    public List<FoodOrderResponseDto> findOrderFood(List<OrderFood> orderFoodList) {
        List<FoodOrderResponseDto> foodOrderResponseDtos = new ArrayList<>();
        for (OrderFood food : orderFoodList) {
            String name = food.getName();
            int quantity = food.getQuantity();
            int price = food.getPrice();

            FoodOrderResponseDto foodOrderResponseDto = new FoodOrderResponseDto(name, quantity, price);
            foodOrderResponseDtos.add(foodOrderResponseDto);
        }
        return foodOrderResponseDtos;
    }
}
