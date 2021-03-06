package com.sparta.spring_w5_homework.service;

import com.sparta.spring_w5_homework.model.Restaurant;
import com.sparta.spring_w5_homework.repository.RestaurantRepository;
import com.sparta.spring_w5_homework.requestdto.RestaurantRequestDto;
import com.sparta.spring_w5_homework.responsedto.RestaurantResponseDto;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;


    //음식점 등록
    @Transactional
    public RestaurantResponseDto restaurantSave(RestaurantRequestDto params) {

        Optional<Restaurant> found = restaurantRepository.findByName(params.getName());

        if (found.isPresent()) {
            throw new IllegalArgumentException("해당 음식점 이름은 이미 존재합니다.");
        }

        if (params.getName().equals("")) {
            throw new IllegalArgumentException("음식점 이름을 입력해주세요.");
        }

        if (params.getMinOrderPrice() < 1000 || params.getMinOrderPrice() > 100000) {
            throw new IllegalArgumentException("최소주문 가격 허용 범위는 1,000원 ~ 100,000원 입니다.");
        } else if (params.getMinOrderPrice() % 100 != 0) {
            throw new IllegalArgumentException("최소주문 가격은 100원 단위로만 입력이 가능합니다.");
        }

        if (params.getDeliveryFee() < 0 || params.getDeliveryFee() > 10000) {
            throw new IllegalArgumentException("기본 배달비 허용 범위는 0원 ~ 10,000원 입니다.");
        } else if (params.getDeliveryFee() % 500 != 0) {
            throw new IllegalArgumentException("기본 배달비는 500원 단위로만 입력 가능합니다.");
        }

        Restaurant restaurant = new Restaurant(params);
        restaurantRepository.save(restaurant);
        return new RestaurantResponseDto(restaurant);
    }

    //음식점 조회
    public List<RestaurantResponseDto> restaurantFindAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Restaurant> list = restaurantRepository.findAll(sort);
        return list.stream().map(RestaurantResponseDto::new).collect(Collectors.toList());
    }
}
