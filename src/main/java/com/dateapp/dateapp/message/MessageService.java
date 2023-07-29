package com.dateapp.dateapp.message;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;

    public MessageService(MessageMapper messageMapper, MessageRepository messageRepository) {
        this.messageMapper = messageMapper;
        this.messageRepository = messageRepository;
    }

    public void save(MessageDto messageDto) {
        Message message = messageMapper.map(messageDto);
        messageRepository.save(message);
    }

    public Set<MessageDto> getChatMessages(long chatId){
        return messageRepository.findAllByChat_Id(chatId).stream()
                .map(messageMapper::map)
                .collect(Collectors.toSet());
    }
}
