package com.dateapp.dateapp.userInfo;

import com.dateapp.dateapp.userInfo.location.LocationDto;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class UserInfoDto {
    @Min(1)
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


}
