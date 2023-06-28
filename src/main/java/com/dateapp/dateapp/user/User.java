package com.dateapp.dateapp.user;

import com.dateapp.dateapp.leftSwiped.LeftSwipedProfile;
import com.dateapp.dateapp.userInfo.UserInfo;
import com.dateapp.dateapp.userRole.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @Email
    private String email;
    @Size(min = 5)
    private String password;
    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;
    @OneToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;
    @OneToMany(mappedBy = "user" )
    private List<LeftSwipedProfile> leftSwipedProfile;

    public List<LeftSwipedProfile> getLeftSwipedProfile() {
        return leftSwipedProfile;
    }

    public void setLeftSwipedProfile(List<LeftSwipedProfile> leftSwipedProfile) {
        this.leftSwipedProfile = leftSwipedProfile;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}