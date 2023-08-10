package com.dateapp.dateapp.config.webSocket.connectionTicket;

import com.dateapp.dateapp.user.User;
import jakarta.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private boolean validity;

    @OneToOne
    private User user;

    public Ticket() {
    }

    public Ticket(String text, User user) {
        this.text = text;
        this.validity = true;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }





    public boolean isValidity() {
        return validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
