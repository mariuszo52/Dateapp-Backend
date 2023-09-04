package com.dateapp.dateapp.userInfo.userInfoEdit;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
@Getter
@Setter
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



}
