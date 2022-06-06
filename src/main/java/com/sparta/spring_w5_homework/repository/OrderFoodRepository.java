package com.sparta.spring_w5_homework.repository;

import com.sparta.spring_w5_homework.model.OrderFood;
import com.sparta.spring_w5_homework.repository.mapping.OrderFoodInfoMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {
    List<OrderFood> findAllByordersId(Long ordersId);
//    List<OrderFoodInfoMapping> findAll();
}
