package com.dateapp.dateapp.message;

import com.dateapp.dateapp.chat.Chat;
import com.dateapp.dateapp.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @Min(1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime sendTime;
    @OneToOne
    private User fromUser;
    @NotNull
    @Size(min = 1, max = 1000)
    private String text;
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;


}