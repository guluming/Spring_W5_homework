package com.sparta.spring_w5_homework.repository;

import com.sparta.spring_w5_homework.model.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByName(String name);
}
