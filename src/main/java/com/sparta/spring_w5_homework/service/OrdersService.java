package com.sparta.spring_w5_homework.service;

import com.sparta.spring_w5_homework.model.OrderFood;
import com.sparta.spring_w5_homework.model.ResFood;
import com.sparta.spring_w5_homework.model.Orders;
import com.sparta.spring_w5_homework.model.Restaurant;

import com.sparta.spring_w5_homework.repository.OrderFoodRepository;
import com.sparta.spring_w5_homework.repository.ResFoodRepository;
import com.sparta.spring_w5_homework.repository.OrdersRepository;
import com.sparta.spring_w5_homework.repository.RestaurantRepository;

import com.sparta.spring_w5_homework.requestdto.OrdersRequestDto;
import com.sparta.spring_w5_homework.requestdto.RestaurantMenuRequestDto;

import com.sparta.spring_w5_homework.responsedto.FoodOrderResponseDto;
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
    private final OrdersRepository ordersRepository;
    private final RestaurantRepository restaurantRepository;
    private final ResFoodRepository resFoodRepository;
    private final OrderFoodRepository orderFoodRepository;


    //주문 정보 등록
    @Transactional
    public OrdersResponseDto saveOrder(Long restaurantId, ArrayList<Map<String, Object>> orderFoods) {

        //주문 음식점 확인
        if (restaurantId == 0) {
            throw new IllegalArgumentException("해당 음식점을 찾을 수 없습니다.");
        }
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NullPointerException("음식점이 존재 하지 않습니다."));

        int totalPrice = 0;

        //주문 정보 등록
        for (Map<String, Object> orderFoodInfo : orderFoods) {
            RestaurantMenuRequestDto result = searchRestaurantMenu(restaurantId, orderFoodInfo);
            totalPrice += result.getPrice();
        }
        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("최소 주문 가격을 확인해 주세요.");
        }
        totalPrice = totalPrice + restaurant.getDeliveryFee();

        OrdersRequestDto params = new OrdersRequestDto(restaurant.getName(), restaurant.getDeliveryFee(), totalPrice);
        Orders orders = new Orders(params);
        Orders ordersId = ordersRepository.save(orders);

        //주문 음식 정보 등록
        List<OrderFood> orderFoodList = saveOrderFood(restaurantId, orderFoods, ordersId);

        //주문 결과 확인
        return new OrdersResponseDto(ordersId.getRestaurantName(), findOrderFood(orderFoodList),
                ordersId.getDeliveryFee(), ordersId.getTotalPrice());
    }

    //주문 음식 정보 등록
    public List<OrderFood> saveOrderFood(Long restaurantId, ArrayList<Map<String, Object>> orderFoods, Orders ordersId) {
        List<OrderFood> orderFoodList = new ArrayList<>();
        for (Map<String, Object> orderFoodInfo : orderFoods) {
            RestaurantMenuRequestDto result = searchRestaurantMenu(restaurantId, orderFoodInfo);

            OrderFood orderFood = new OrderFood(ordersId, result.getName(), result.getQuantity(), result.getPrice());
            orderFoodList.add(orderFood);
        }
        return orderFoodRepository.saveAll(orderFoodList);
    }

    //주문 음식 메뉴판에서 검색
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

    //주문 정보 전체 조회
    public List<OrdersResponseDto> findAllOrder() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<OrdersResponseDto> ordersResponseDtos = new ArrayList<>();
        List<Orders> orderList = ordersRepository.findAll(sort);
        for (Orders orders : orderList) {

            List<OrderFood> orderFoodList = orderFoodRepository.findAllByOrdersId(orders);

            OrdersResponseDto ordersResponseDto = new OrdersResponseDto(orders.getRestaurantName(),
                    findOrderFood(orderFoodList), orders.getDeliveryFee(), orders.getTotalPrice());

            ordersResponseDtos.add(ordersResponseDto);
        }
        return ordersResponseDtos;
    }

    //주문 음식 정보 조회
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
