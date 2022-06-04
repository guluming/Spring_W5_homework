package com.sparta.spring_w5_homework.repository;

import com.sparta.spring_w5_homework.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {}
