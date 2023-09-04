package com.dateapp.dateapp.userInfo.location;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDto {
    private Long id;
    @NotNull
    @NotBlank
    @Size(min = 1)
    private String name;
    @NotNull
    @Size(max = 3)
    private String country;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

}
