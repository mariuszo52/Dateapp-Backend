package com.dateapp.dateapp.match;

import com.dateapp.dateapp.chat.Chat;
import com.dateapp.dateapp.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Match {
    @Id
    @Min(1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Boolean isMatched;
    @ManyToOne
    private User user;
    @ManyToOne
    private User matchedUser;
    @ManyToOne
    private Chat chat;
    @NotNull
    private LocalDate matchDate;

}