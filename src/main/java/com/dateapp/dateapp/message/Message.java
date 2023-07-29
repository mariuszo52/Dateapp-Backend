package com.dateapp.dateapp.message;

import com.dateapp.dateapp.chat.Chat;
import com.dateapp.dateapp.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime sendTime;
    @OneToOne
    private User fromUser;
    private String text;
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public Message(Long id, User fromUser, String text, Chat chat) {
        this.id = id;
        this.sendTime = LocalDateTime.now();
        this.fromUser = fromUser;
        this.text = text;
        this.chat = chat;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public Message() {

    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
