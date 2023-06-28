package com.dateapp.dateapp.leftSwiped;

import com.dateapp.dateapp.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "left_swipe")
public class LeftSwipedProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private User leftSwipedProfile;

    public LeftSwipedProfile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getLeftSwipedProfile() {
        return leftSwipedProfile;
    }

    public void setLeftSwipedProfile(User leftSwipedProfile) {
        this.leftSwipedProfile = leftSwipedProfile;
    }
}
