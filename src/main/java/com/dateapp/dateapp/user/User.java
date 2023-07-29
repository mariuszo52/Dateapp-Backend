package com.dateapp.dateapp.user;

import com.dateapp.dateapp.chat.Chat;
import com.dateapp.dateapp.match.Match;
import com.dateapp.dateapp.swipedProfile.SwipedProfile;
import com.dateapp.dateapp.userInfo.UserInfo;
import com.dateapp.dateapp.userRole.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private List<SwipedProfile> swipedProfiles;
    @OneToMany(mappedBy = "user")
    private List<Match> matches;
    @ManyToMany(mappedBy = "participants")
    private Set<Chat> chats = new HashSet<>();

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public Set<Chat> getChats() {
        return chats;
    }

    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public List<SwipedProfile> getSwipedProfiles() {
        return swipedProfiles;
    }

    public void setSwipedProfiles(List<SwipedProfile> swipedProfiles) {
        this.swipedProfiles = swipedProfiles;
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