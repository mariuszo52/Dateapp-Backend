package com.dateapp.dateapp.match;

import com.dateapp.dateapp.chat.Chat;
import com.dateapp.dateapp.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isMatched;
    @ManyToOne
    private User user;
    @ManyToOne
    private User matchedUser;
    private LocalDate matchDate;

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }


    public Boolean getMatched() {
        return isMatched;
    }

    public void setMatched(Boolean matched) {
        isMatched = matched;
    }

    public Match() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getMatchedUser() {
        return matchedUser;
    }

    public void setMatchedUser(User matchedUser) {
        this.matchedUser = matchedUser;
    }
}
