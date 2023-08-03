package com.dateapp.dateapp.message;

import com.dateapp.dateapp.exceptions.EmptyMessageException;
import com.dateapp.dateapp.exceptions.MessageToLongException;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final static int MAX_MESSAGE_SIZE = 1000;
    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;

    public MessageService(MessageMapper messageMapper, MessageRepository messageRepository) {
        this.messageMapper = messageMapper;
        this.messageRepository = messageRepository;
    }

    public void checkDurationMessage(MessageDto message) {
        if (message.getText().equals("")){
            throw new EmptyMessageException();
        }
        if (message.getText().length() > MAX_MESSAGE_SIZE){
            throw new MessageToLongException();
        }
    }
    public void save(MessageDto messageDto) {
        Message message = messageMapper.map(messageDto);
        messageRepository.save(message);
    }

    public Page<MessageDto> getChatMessages(long chatId, int page, int size){
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        List<MessageDto> messagesList = messageRepository.findAllByChat_Id(chatId, pageable).stream()
                .map(messageMapper::map)
                .collect(Collectors.toList());
        return new PageImpl<>(messagesList, pageable, messagesList.size());
    }
}
