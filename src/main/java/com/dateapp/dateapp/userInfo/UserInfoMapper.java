package com.dateapp.dateapp.userInfo;

import com.dateapp.dateapp.userInfo.location.LocationMapper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserInfoMapper {
    public static UserInfo map(UserInfoDto userInfoDto) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userInfoDto.getId());
        LocalDate dateOfBirth = LocalDate.of(userInfoDto.getYearOfBirth(),
                userInfoDto.getMonthOfBirth(), userInfoDto.getDayOfBirth());
        userInfo.setDateOfBirth(dateOfBirth);
        userInfo.setFirstName(userInfoDto.getFirstName());
        userInfo.setGenderIdentity(userInfoDto.getGenderIdentity());
        userInfo.setLocation(LocationMapper.map(userInfoDto.getLocationDto()));
        userInfo.setGenderInterest(userInfoDto.getGenderInterest());
        userInfo.setUrl(userInfoDto.getUrl());
        userInfo.setAbout(userInfoDto.getAbout());
        userInfo.setMaxDistance(userInfoDto.getMaxDistance());
        return userInfo;
    }

    public static UserInfoDto map(UserInfo userInfo) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(userInfo.getId());
        userInfoDto.setFirstName(userInfo.getFirstName());
        userInfoDto.setDayOfBirth(userInfo.getDateOfBirth().getDayOfMonth());
        userInfoDto.setMonthOfBirth(userInfo.getDateOfBirth().getMonthValue());
        userInfoDto.setYearOfBirth(userInfo.getDateOfBirth().getYear());
        userInfoDto.setLocationDto(LocationMapper.map(userInfo.getLocation()));
        userInfoDto.setGenderIdentity(userInfo.getGenderIdentity());
        userInfoDto.setGenderInterest(userInfo.getGenderInterest());
        userInfoDto.setUrl(userInfo.getUrl());
        userInfoDto.setAbout(userInfo.getAbout());
        userInfoDto.setMaxDistance(userInfo.getMaxDistance());
        int age = (int) ChronoUnit.YEARS.between(userInfo.getDateOfBirth(), LocalDate.now());
        userInfoDto.setAge(age);
        return userInfoDto;
    }


}
