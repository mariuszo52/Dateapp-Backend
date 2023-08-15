package com.dateapp.dateapp.userInfo;

import java.time.LocalDate;

public class UserInfoMapper {
    static UserInfo map(UserInfoDto userInfoDto) {
        UserInfo userInfo = new UserInfo();
        LocalDate dateOfBirth = LocalDate.of(userInfoDto.getYearOfBirth(),
                userInfoDto.getMonthOfBirth(), userInfoDto.getDayOfBirth());
        userInfo.setDateOfBirth(dateOfBirth);
        userInfo.setFirstName(userInfoDto.getFirstName());
        userInfo.setLocation(userInfoDto.getLocation());
        userInfo.setGenderIdentity(userInfoDto.getGenderIdentity());
        userInfo.setGenderInterest(userInfoDto.getGenderInterest());
        userInfo.setUrl(userInfoDto.getUrl());
        userInfo.setAbout(userInfoDto.getAbout());
        userInfo.setUserId(userInfoDto.getUserId());
        return userInfo;
    }

    public static UserInfoDto map(UserInfo userInfo){
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(userInfo.getId());
        userInfoDto.setFirstName(userInfo.getFirstName());
        userInfoDto.setDayOfBirth(userInfo.getDateOfBirth().getDayOfMonth());
        userInfoDto.setMonthOfBirth(userInfo.getDateOfBirth().getMonthValue());
        userInfoDto.setYearOfBirth(userInfo.getDateOfBirth().getYear());
        userInfoDto.setLocation(userInfo.getLocation());
        userInfoDto.setGenderIdentity(userInfo.getGenderIdentity());
        userInfoDto.setGenderInterest(userInfo.getGenderInterest());
        userInfoDto.setUrl(userInfo.getUrl());
        userInfoDto.setAbout(userInfo.getAbout());
        userInfoDto.setUserId(userInfo.getUserId());
        userInfoDto.setAge();
        return userInfoDto;
    }
}
