package com.sparta.spring_w5_homework.repository;

import com.sparta.spring_w5_homework.model.ResFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResFoodRepository extends JpaRepository<ResFood, Long> {
    List<ResFood> findByrestaurantId(Long restaurantId);
    Optional<ResFood> findByRestaurantIdAndName(Long restaurantId, String name);
    Optional<ResFood> findByRestaurantIdAndId(Long restaurantId, Long Id);
}
