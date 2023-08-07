package com.dateapp.dateapp.message;

import com.dateapp.dateapp.chat.Chat;
import com.dateapp.dateapp.chat.ChatRepository;
import com.dateapp.dateapp.exceptions.user.UserNotFoundException;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageMapper {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    MessageMapper(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public Message map(MessageDto messageDto){
        Message message = new Message();
        message.setSendTime(LocalDateTime.now());
        User fromUser = userRepository.findById(messageDto.getFromUserId()).orElseThrow(UserNotFoundException::new);
        message.setFromUser(fromUser);
        message.setText(messageDto.getText());
        Chat chat = chatRepository.findById(messageDto.getChatId()).orElseThrow();
        message.setChat(chat);
        return message;
    }
    public MessageDto map(Message message){
        MessageDto messageDto = new MessageDto();
        messageDto.setSendTime(message.getSendTime());
        messageDto.setId(message.getId());
        messageDto.setFromUserId(message.getFromUser().getId());
        messageDto.setText(message.getText());
        messageDto.setChatId(message.getChat().getId());
        return messageDto;
    }
}
