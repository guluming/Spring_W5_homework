package com.sparta.spring_w5_homework.repository;

import com.sparta.spring_w5_homework.model.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {
}
