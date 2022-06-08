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


    //주문
    @Transactional
    public OrdersResponseDto ordersSave(Long restaurantId, ArrayList<Map<String,Object>> orderfoods) {
        if (restaurantId == 0) {
            throw new IllegalArgumentException("해당 음식점을 찾을 수 없습니다.");
//            return "해당 음식점을 찾을 수 없습니다.";
        }
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NullPointerException("음식점이 존재 하지 않습니다."));

        //쓰이는 곳이 한 곳이면 선언하지말고 바로 넣기
        String restaurantName = restaurant.getName();
        int deliveryFee = restaurant.getDeliveryFee();
        int minOrderPrice = restaurant.getMinOrderPrice();
        int totalPrice = 0;

        for (int i = 0; i < orderfoods.size(); i++) {

            Long foodId = ((Number) orderfoods.get(i).get("id")).longValue();
            int quantity = (Integer) orderfoods.get(i).get("quantity");

            ResFood orderResFood = resFoodRepository.findByRestaurantIdAndId(restaurantId, foodId).orElseThrow(
                    () -> new NullPointerException("해당 음식은 해당 음식점에 없습니다."));

            if (1 <= quantity && quantity <= 100) {

                int price = quantity * orderResFood.getPrice();

                totalPrice += price;
            } else {
                throw new IllegalArgumentException("주문 가능한 음식 수량은 1개 ~ 100개 입니다.");
            }
        }

        if (totalPrice < minOrderPrice) {
            throw new IllegalArgumentException("최소 주문 가격을 확인해 주세요.");
        }

        totalPrice = totalPrice + deliveryFee;

        OrdersRequestDto params = new OrdersRequestDto(restaurantName, deliveryFee, totalPrice);
        Orders orders = new Orders(params);
        Orders ordersId = ordersRepository.save(orders);


        List<OrderFood> foods = new ArrayList<>();

        for (int i = 0; i < orderfoods.size(); i++) {

            Long foodId = ((Number) orderfoods.get(i).get("id")).longValue();
            int quantity = (Integer) orderfoods.get(i).get("quantity");

            ResFood orderResFood = resFoodRepository.findByRestaurantIdAndId(restaurantId, foodId).orElseThrow(
                    () -> new NullPointerException("해당 음식은 해당 음식점에 없습니다."));

            int price = quantity * orderResFood.getPrice();

            String name = orderResFood.getName();

            OrderFood orderFood = new OrderFood(ordersId, name, quantity, price);
            foods.add(orderFood);
        }
        orderFoodRepository.saveAll(foods);


        List<FoodOrderResponseDto> foodOrderResponseDtos = new ArrayList<>();

        for (int i = 0; i < foods.size(); i++) {
            String name = foods.get(i).getName();
            int quantity = foods.get(i).getQuantity();
            int price = foods.get(i).getPrice();

            FoodOrderResponseDto foodOrderResponseDto = new FoodOrderResponseDto(name, quantity, price);

            foodOrderResponseDtos.add(foodOrderResponseDto);

        }
        OrdersResponseDto ordersResponseDto = new OrdersResponseDto(ordersId.getRestaurantName(), foodOrderResponseDtos,
                ordersId.getDeliveryFee(), ordersId.getTotalPrice());

        return ordersResponseDto;
    }

    //주문 전체 조회
    public List<OrdersResponseDto> ordersFindAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<OrdersResponseDto> ordersResponseDtos = new ArrayList<>();
        List<FoodOrderResponseDto> foodOrderResponseDtos = new ArrayList<>();
        List<Orders> orderList = ordersRepository.findAll(sort);

        for (int i = 0; i < orderList.size(); i++) {

            List<OrderFood> orderFoodList = orderFoodRepository.findAllByordersId(orderList.get(i));

            for (int j = 0; j < orderFoodList.size(); j++) {

                String name = orderFoodList.get(j).getName();
                int quantity = orderFoodList.get(j).getQuantity();
                int price = orderFoodList.get(j).getPrice();

                FoodOrderResponseDto foodOrderResponseDto = new FoodOrderResponseDto(name, quantity, price);
                foodOrderResponseDtos.add(foodOrderResponseDto);
            }

            OrdersResponseDto ordersResponseDto = new OrdersResponseDto(orderList.get(i).getRestaurantName(),
                    foodOrderResponseDtos, orderList.get(i).getDeliveryFee(), orderList.get(i).getTotalPrice());

            ordersResponseDtos.add(ordersResponseDto);
        }
        return ordersResponseDtos;
    }
}
