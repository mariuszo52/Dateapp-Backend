package com.dateapp.dateapp.chat;

import com.dateapp.dateapp.message.Message;
import com.dateapp.dateapp.user.User;

import java.util.Set;
import java.util.stream.Collectors;

class ChatMapper {
    static ChatDto map(Chat chat){
        ChatDto chatDto = new ChatDto();
        chatDto.setId(chat.getId());
        Set<Long> participantsIds = chat.getParticipants().stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        chatDto.setParticipantsIds(participantsIds);
        return chatDto;
    }
}
