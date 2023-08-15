package com.dateapp.dateapp.chat;

import com.dateapp.dateapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
    Optional<ChatUser> findByChat_IdAndUserId(long chatId, long userId);
    List<ChatUser> findByUser_Id(long userId);
}
