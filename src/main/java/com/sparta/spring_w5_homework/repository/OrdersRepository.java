package com.sparta.spring_w5_homework.repository;

import com.sparta.spring_w5_homework.model.Orders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByOrderByIdAsc();
}
