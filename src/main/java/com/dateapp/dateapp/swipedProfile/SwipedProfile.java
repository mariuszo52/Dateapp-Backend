package com.dateapp.dateapp.swipedProfile;

import com.dateapp.dateapp.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "swiped_profile")
public class SwipedProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String swipeDirection;
    private final LocalDateTime swipeTime = LocalDateTime.now();
    @ManyToOne
    private User user;
    @ManyToOne
    private User swipedProfile;

    public SwipedProfile() {
    }

    public LocalDateTime getSwipeTime() {
        return swipeTime;
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

    public String getSwipeDirection() {
        return swipeDirection;
    }

    public void setSwipeDirection(String swipeDirection) {
        this.swipeDirection = swipeDirection;
    }

    public User getSwipedProfile() {
        return swipedProfile;
    }

    public void setSwipedProfile(User swipedProfile) {
        this.swipedProfile = swipedProfile;
    }
}
