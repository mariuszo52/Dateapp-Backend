package com.dateapp.dateapp.user;

import com.dateapp.dateapp.chat.Chat;
import com.dateapp.dateapp.config.webSocket.connectionTicket.Ticket;
import com.dateapp.dateapp.match.Match;
import com.dateapp.dateapp.swipedProfile.SwipedProfile;
import com.dateapp.dateapp.userInfo.UserInfo;
import com.dateapp.dateapp.userRole.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Email
    private String email;
    @Size(min = 5)
    private String password;
    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<SwipedProfile> swipedProfiles;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Match> matches;
    @ManyToMany(mappedBy = "participants", cascade = CascadeType.REMOVE)
    private Set<Chat> chats = new HashSet<>();

    public User(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}