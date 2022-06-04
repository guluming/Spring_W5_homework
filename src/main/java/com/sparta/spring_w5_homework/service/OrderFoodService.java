package com.sparta.spring_w5_homework.service;

import com.sparta.spring_w5_homework.model.ResFood;
import com.sparta.spring_w5_homework.model.OrderFood;
import com.sparta.spring_w5_homework.model.Orders;
import com.sparta.spring_w5_homework.repository.ResFoodRepository;
import com.sparta.spring_w5_homework.repository.OrderFoodRepository;
import com.sparta.spring_w5_homework.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderFoodService {
    private final OrderFoodRepository orderFoodRepository;
    private final OrdersRepository ordersRepository;
    private final ResFoodRepository resFoodRepository;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //음식 주문
    @Transactional
    public boolean orderFoodSave(Long restaurantId, List<List<String>> foods){
        LocalDateTime modifiedAt = LocalDateTime.now();

        for(int i=0; i<foods.size(); i++){
            List<String> food = foods.get(i);

            if(food.size() == 0){
                return false;
            }

            Long foodId = Long.parseLong(food.get(0));
            int quantity = Integer.parseInt(food.get(1));

            ResFood orderFood = resFoodRepository.findByRestaurantIdAndId(restaurantId, foodId).orElseThrow(() -> new NullPointerException("해당 음식은 해당 음식점에 없습니다."));

            int price = quantity * orderFood.getPrice();

            Orders orders = ordersRepository.findBymodifiedAt(modifiedAt).orElseThrow(() -> new NullPointerException("주문내역이 없습니다."));
            Long ordersId = orders.getId();

            OrderFood orderFoods = new OrderFood(ordersId, orderFood.getName(), quantity, price, modifiedAt);
            orderFoodRepository.save(orderFoods);
        }
        return true;
    }
}
