package com.dateapp.dateapp.userInfo;

import com.dateapp.dateapp.userInfo.location.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class UserInfo {
    @Id
    @Min(1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 1)
    private String firstName;
    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private String genderIdentity;
    @NotNull
    private String genderInterest;
    @NotNull
    @URL
    private String url;
    @Size(min = 1, max = 1000)
    private String about;
    @NotNull
    private Double maxDistance;

}
