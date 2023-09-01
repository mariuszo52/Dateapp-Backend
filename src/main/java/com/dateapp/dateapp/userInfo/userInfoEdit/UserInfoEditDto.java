package com.dateapp.dateapp.userInfo.userInfoEdit;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public class UserInfoEditDto {
    @NotNull
    @Size(min = 1)
    @NotBlank
    private String firstName;
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
    @NotBlank
    @Size(min = 1)
    private String locationName;
    @NotNull
    private Double locationLatitude;
    @NotNull
    private Double locationLongitude;
    @NotNull
    @Size(max = 3)
    private String locationCountry;
    @NotNull
    @URL
    private String url;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 1000)
    private String about;

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(Double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public Double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(Double locationLongitude) {
        this.locationLongitude = locationLongitude;
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
