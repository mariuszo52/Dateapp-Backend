package com.dateapp.dateapp.userInfo;

import com.dateapp.dateapp.userInfo.location.Location;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    @OneToOne
    @JoinColumn(name = "location_id" )
    private Location location;
    private LocalDate dateOfBirth;
    private String genderIdentity;
    private String genderInterest;
    private String url;
    private String about;
    private Long userId;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", location=" + location +
                ", dateOfBirth=" + dateOfBirth +
                ", genderIdentity='" + genderIdentity + '\'' +
                ", genderInterest='" + genderInterest + '\'' +
                ", url='" + url + '\'' +
                ", about='" + about + '\'' +
                ", userId=" + userId +
                '}';
    }
}
