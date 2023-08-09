package com.dateapp.dateapp.config.webSocket.connectionTicket;

import com.dateapp.dateapp.user.User;
import jakarta.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    private boolean validity;
    @OneToOne
    private User user;

    public Ticket() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Ticket(String value, User user) {
        this.value = value;
        this.user = user;
        this.validity = true;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isValidity() {
        return validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", validity=" + validity +
                ", user=" + user +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
