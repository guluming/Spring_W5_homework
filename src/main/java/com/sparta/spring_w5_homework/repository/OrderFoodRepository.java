package com.sparta.spring_w5_homework.repository;

import com.sparta.spring_w5_homework.model.OrderFood;
import com.sparta.spring_w5_homework.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {
    Optional<OrderFood> findAllBymodifiedAt(LocalDateTime modifiedAt);
}
