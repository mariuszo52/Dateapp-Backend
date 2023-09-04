package com.dateapp.dateapp.swipedProfile;

import com.dateapp.dateapp.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

}
