package com.sparta.spring_w5_homework.service;

import com.sparta.spring_w5_homework.model.ResFood;
import com.sparta.spring_w5_homework.repository.ResFoodRepository;
import com.sparta.spring_w5_homework.requestdto.ResFoodRequestDto;
import com.sparta.spring_w5_homework.requestdto.ResFoodRequestListDto;
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
    public String foodSave(Long restaurantId, List<ResFoodRequestListDto> resFoodDtoList){
//        LocalDateTime modifiedAt = LocalDateTime.now();

        if(restaurantId == 0){
            return "음식점 ID를 입력해주세요.";
        }

        for(int i = 0; i < resFoodDtoList.size(); i++){

            Optional<ResFood> found = resFoodRepository.findByRestaurantIdAndName(restaurantId, resFoodDtoList.get(i).getName());

            if(found.isPresent()){
                return "이미 음식이 존재합니다.";
            }

            if(resFoodDtoList.get(i).getPrice() > 100 && resFoodDtoList.get(i).getPrice() < 1000000){

                ResFoodRequestDto params = new ResFoodRequestDto(resFoodDtoList.get(i).getName(), resFoodDtoList.get(i).getPrice(), restaurantId);

                ResFood resFood = new ResFood(params);
                resFoodRepository.save(resFood);

            } else {
                return "음식 가격의 허용 범위는 100원 ~ 1,000,000원 입니다.";
            }

            if (resFoodDtoList.get(i).getPrice()%100 != 0) {
                return "음식 가격은 100원 단위로만 입력 가능합니다.";
            }
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
