package com.dateapp.dateapp.like;

import com.dateapp.dateapp.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime time;
    @ManyToOne
    @JoinColumn(name = "sending_user_id")
    private User sendingUser;
    @ManyToOne
    @JoinColumn(name = "receiving_user_id")
    private User receivingUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getSendingUser() {
        return sendingUser;
    }

    public void setSendingUser(User sendingUser) {
        this.sendingUser = sendingUser;
    }

    public User getReceivingUser() {
        return receivingUser;
    }

    public void setReceivingUser(User receivingUser) {
        this.receivingUser = receivingUser;
    }

    public Like() {
    }
}


