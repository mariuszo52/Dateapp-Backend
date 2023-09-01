package com.dateapp.dateapp.userInfo;

import com.dateapp.dateapp.userInfo.location.LocationDto;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserInfoDto {
    private long id;
    @NotNull
    @Size(min = 1)
    @NotBlank
    private String firstName;
    private int age;
    @NotNull
    @Min(1)
    @Max(31)
    private int dayOfBirth;
    @NotNull
    @Min(1)
    @Max(12)
    private int monthOfBirth;
    @NotNull
    @Min(1950)
    @Max(2005)
    private int yearOfBirth;
    @NotNull
    private LocationDto locationDto;
    @NotNull
    private String genderIdentity;
    @NotNull
    private String genderInterest;
    @NotNull
    @URL
    private String url;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 1000)
    private String about;
    @NotNull
    @Min(0)
    @Max(500)
    private Double maxDistance;

    public Double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Double maxDistance) {
        this.maxDistance = maxDistance;
    }


    public LocationDto getLocationDto() {
        return locationDto;
    }

    public void setLocationDto(LocationDto locationDto) {
        this.locationDto = locationDto;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge() {
        this.age = (int) ChronoUnit.YEARS.between(LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth), LocalDate.now());
    }


    public UserInfoDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }


    public String getGenderIdentity() {
        return genderIdentity;
    }

    public void setGenderIdentity(String genderIdentity) {
        this.genderIdentity = genderIdentity;
    }

    public String getGenderInterest() {
        return genderInterest;
    }

    public void setGenderInterest(String genderInterest) {
        this.genderInterest = genderInterest;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
