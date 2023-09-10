package com.dateapp.dateapp.chat;

import com.dateapp.dateapp.match.Match;
import com.dateapp.dateapp.message.Message;
import com.dateapp.dateapp.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Chat {
    @Id
    @Min(1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name = "chat_users",
            joinColumns = @JoinColumn(name = "chat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> participants = new ArrayList<>();
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "chat")
    private Set<Message> messages = new HashSet<>();
    @OneToMany(mappedBy = "chat")
    private List<Match> match = new ArrayList<>();
}

