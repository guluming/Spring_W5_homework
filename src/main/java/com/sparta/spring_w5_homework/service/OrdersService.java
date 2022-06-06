package com.sparta.spring_w5_homework.service;

import com.sparta.spring_w5_homework.model.OrderFood;
import com.sparta.spring_w5_homework.model.ResFood;
import com.sparta.spring_w5_homework.model.Orders;
import com.sparta.spring_w5_homework.model.Restaurant;
import com.sparta.spring_w5_homework.repository.OrderFoodRepository;
import com.sparta.spring_w5_homework.repository.ResFoodRepository;
import com.sparta.spring_w5_homework.repository.OrdersRepository;
import com.sparta.spring_w5_homework.repository.RestaurantRepository;
import com.sparta.spring_w5_homework.requestdto.OrderFoodsListDto;
import com.sparta.spring_w5_homework.requestdto.OrdersRequestDto;
import com.sparta.spring_w5_homework.responsedto.OrdersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final RestaurantRepository restaurantRepository;
    private final ResFoodRepository resFoodRepository;
    private final OrderFoodRepository orderFoodRepository;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //주문
    @Transactional
    public OrdersResponseDto ordersSave(Long restaurantId, List<OrderFoodsListDto> orderFoodsDtoList) {
//        LocalDateTime modifiedAt = LocalDateTime.now();

        if(restaurantId == 0L){
            throw new IllegalArgumentException("해당 음식점을 찾을 수 없습니다.");
//            return "해당 음식점을 찾을 수 없습니다.";
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new NullPointerException("음식점이 존재 하지 않습니다."));

//        Long resId = restaurant.getId();
        String restaurantName = restaurant.getName();
//        Long foods = restaurantId;
        int deliveryFee = restaurant.getDeliveryFee();
        int totalPrice = 0;

        List<OrderFood> foods = new ArrayList<>();

        for(int j=0; j<orderFoodsDtoList.size(); j++){

            Long foodId = orderFoodsDtoList.get(j).getFoodId();
            int quantity = orderFoodsDtoList.get(j).getQuantity();

            ResFood orderResFood = resFoodRepository.findByRestaurantIdAndId(restaurantId, foodId).orElseThrow(() -> new NullPointerException("해당 음식은 해당 음식점에 없습니다."));

            String foodName = orderResFood.getName();
            int price = quantity * orderResFood.getPrice();

            OrderFood orderFood = new OrderFood();
            orderFood.setFoodName(foodName);
            orderFood.setQuantity(quantity);
            orderFood.setPrice(price);

            foods.add(orderFood);

            totalPrice += price;

        }

        totalPrice = totalPrice + deliveryFee;


        OrdersRequestDto params = new OrdersRequestDto(restaurantName, foods, deliveryFee, totalPrice);
        Orders orders = new Orders(params);

        orderFoodRepository.saveAll(foods);
        ordersRepository.save(orders);
        return new OrdersResponseDto(orders);
    }

    //주문 전체 조회
//    public List<OrdersResponseDto> ordersFindAll(){
//        Sort sort = Sort.by(Sort.Direction.ASC, "id");
//        List<Orders> list = ordersRepository.findAll(sort);
//        return list.stream().map(OrdersResponseDto::new).collect(Collectors.toList());
//    }
}
