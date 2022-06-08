package com.sparta.spring_w5_homework.service;

import com.sparta.spring_w5_homework.model.ResFood;
import com.sparta.spring_w5_homework.repository.ResFoodRepository;
import com.sparta.spring_w5_homework.requestdto.ResFoodRequestDto;
import com.sparta.spring_w5_homework.requestdto.ResFoodRequestListDto;
import com.sparta.spring_w5_homework.responsedto.ResFoodResponseDto;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResFoodService {
    private final ResFoodRepository resFoodRepository;

    //음식 등록
    @Transactional
    public void saveResFood(Long restaurantId, List<ResFoodRequestListDto> resFoodDtoList) {

        if (restaurantId == 0) {
            throw new IllegalArgumentException("음식점 ID를 입력해주세요.");
        }

        for (ResFoodRequestListDto resFoodRequestListDto : resFoodDtoList) {

            Optional<ResFood> found = resFoodRepository.findByRestaurantIdAndName(restaurantId, resFoodRequestListDto.getName());

            if (found.isPresent()) {
                throw new IllegalArgumentException("이미 음식이 존재합니다.");
            }

            if (resFoodRequestListDto.getPrice() < 100 || resFoodRequestListDto.getPrice() > 1000000) {
                throw new IllegalArgumentException("음식 가격의 허용 범위는 100원 ~ 1,000,000원 입니다.");
            } else if (resFoodRequestListDto.getPrice() % 100 != 0) {
                throw new IllegalArgumentException("음식 가격은 100원 단위로만 입력 가능합니다.");
            } else {
                ResFoodRequestDto params = new ResFoodRequestDto(resFoodRequestListDto.getName(), resFoodRequestListDto.getPrice(), restaurantId);
                ResFood resFood = new ResFood(params);
                resFoodRepository.save(resFood);
            }
        }
    }

    //메뉴판 조회
    public List<ResFoodResponseDto> findByRestaurantMenu(Long restaurantId) {
        List<ResFood> list = resFoodRepository.findByRestaurantId(restaurantId);
        list.sort(new Comparator<ResFood>() {
            @Override
            public int compare(ResFood o1, ResFood o2) {
                if (o1.getId() < o2.getId()) {
                    return -1;
                } else if (o1.getId() > o2.getId()) {
                    return 1;
                }
                return 0;
            }
        });
        return list.stream().map(ResFoodResponseDto::new).collect(Collectors.toList());
    }
}
