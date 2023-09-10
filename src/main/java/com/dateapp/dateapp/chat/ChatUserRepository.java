package com.dateapp.dateapp.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
    Optional<ChatUser> findByChat_IdAndUserId(long chatId, long userId);

    List<ChatUser> findByUser_Id(long userId);
}
