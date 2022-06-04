package com.sparta.spring_w5_homework.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spring_w5_homework.model.ResFood;
import com.sparta.spring_w5_homework.repository.ResFoodRepository;
import com.sparta.spring_w5_homework.requestdto.ResFoodRequestDto;
import com.sparta.spring_w5_homework.responsedto.ResFoodResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResFoodService {
    private final ResFoodRepository resFoodRepository;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //음식 등록
    @Transactional
    public String foodSave(Long restaurantId, ResFoodRequestDto resFoods){
        LocalDateTime modifiedAt = LocalDateTime.now();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String jsonSting = objectMapper.writeValueAsString(resFoods);
        DTO Dtos[] = objectMapper.readValue(jsonSting,Event[].class);

        List<ResFoodRequestDto.Foods> foods = resFoods.getFoods();
        if(restaurantId == 0){
            return "음식점 ID를 입력해주세요.";
        }

        for(int i = 0; i < foods.size(); i++){
            food = foods.get(i);

            if(food.size() == 0){
                return "등록하실 음식을 적어주세요.";
            }

            String name = food.get(0);
            int price = Integer.parseInt(food.get(1));

            Optional<ResFood> found = resFoodRepository.findByRestaurantIdAndName(restaurantId, name);

            if(found.isPresent()){
                return "이미 음식이 존재합니다.";
            }

            if(price < 100 && price > 1000000){
                return "음식 가격의 허용 범위는 100원 ~ 1,000,000원 입니다.";
            } else if (price%100 != 0) {
                return "음식 가격은 100원 단위로만 입력 가능합니다.";
            }

            ResFood resFood = new ResFood(name, price, restaurantId, modifiedAt);
            resFoodRepository.save(resFood);
        }
        return "음식이 등록 되었습니다.";
    }

    //메뉴판 조회
    public List<ResFoodResponseDto> findByMenu(Long restaurantId){
        List<ResFood> list = resFoodRepository.findByrestaurantId(restaurantId);
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
