package com.dateapp.dateapp.config.webSocket.connectionTicket;

import com.dateapp.dateapp.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ticket {
    @Id
    @Min(1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 20, max = 20)
    private String text;

    @OneToOne
    private User user;

    public Ticket(String text, User user) {
        this.text = text;
        this.user = user;
    }

}