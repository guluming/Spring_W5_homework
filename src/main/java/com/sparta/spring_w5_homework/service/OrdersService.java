package com.sparta.spring_w5_homework.service;

import com.sparta.spring_w5_homework.model.ResFood;
import com.sparta.spring_w5_homework.model.Orders;
import com.sparta.spring_w5_homework.model.Restaurant;
import com.sparta.spring_w5_homework.repository.ResFoodRepository;
import com.sparta.spring_w5_homework.repository.OrdersRepository;
import com.sparta.spring_w5_homework.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final RestaurantRepository restaurantRepository;
    private final ResFoodRepository resFoodRepository;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //주문
    @Transactional
    public boolean ordersSave(Long restaurantId, List<List<String>> foods){
        LocalDateTime modifiedAt = LocalDateTime.now();
        int totalPrice = 0;

        if(restaurantId == 0L){
            return false;
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new NullPointerException("음식점이 존재 하지 않습니다."));

        Long resId = restaurant.getId();
        String restaurantName = restaurant.getName();
        int deliveryFee = restaurant.getDeliveryFee();

        for(int i=0; i<foods.size(); i++){
            List<String> food = foods.get(i);

            if(food.size() == 0){
                return false;
            }

            Long foodId = Long.parseLong(food.get(0));
            int quantity = Integer.parseInt(food.get(1));

            ResFood orderResFood = resFoodRepository.findByRestaurantIdAndId(restaurantId, foodId).orElseThrow(() -> new NullPointerException("해당 음식은 해당 음식점에 없습니다."));

            int price = quantity * orderResFood.getPrice();

            totalPrice = totalPrice + price;
        }

        Orders orders = new Orders(resId, restaurantName, deliveryFee, totalPrice, modifiedAt);
        ordersRepository.save(orders);
        return true;
    }

    //주문 조회
//    public List<OrdersResponseDto> ordersFindAll(){
//        List<Orders> list = ordersRepository.findAll();
//        return list.stream().map(OrdersResponseDto::new).collect(Collectors.toList());
//    }
}
