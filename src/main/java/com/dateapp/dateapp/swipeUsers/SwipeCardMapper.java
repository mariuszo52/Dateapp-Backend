package com.dateapp.dateapp.swipeUsers;

import com.dateapp.dateapp.user.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class SwipeCardMapper {

    public static SwipeCardDto map(User user){
        SwipeCardDto swipeCardDto = new SwipeCardDto();
        swipeCardDto.setUserId(user.getId());
        swipeCardDto.setFirstName(user.getUserInfo().getFirstName());
        int age = (int) ChronoUnit.YEARS.between(user.getUserInfo().getDateOfBirth(), LocalDate.now());
        swipeCardDto.setAge(age);
        swipeCardDto.setLocationName(user.getUserInfo().getLocation().getName());
        swipeCardDto.setUrl(user.getUserInfo().getUrl());
        swipeCardDto.setAbout(user.getUserInfo().getAbout());
        return swipeCardDto;
    }
}
