package com.dateapp.dateapp.message;

import com.dateapp.dateapp.chat.Chat;
import com.dateapp.dateapp.chat.ChatRepository;
import com.dateapp.dateapp.exceptions.UnauthorizedResourceAccessException;
import com.dateapp.dateapp.exceptions.chat.ChatNotFoundException;
import com.dateapp.dateapp.exceptions.message.EmptyMessageException;
import com.dateapp.dateapp.exceptions.message.MessageToLongException;
import com.dateapp.dateapp.exceptions.message.MessageWhiteSpacesException;
import com.dateapp.dateapp.user.User;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static com.dateapp.dateapp.config.security.LoggedUserService.getLoggedUserId;

@Service
public class MessageService {
    private final static int MAX_MESSAGE_SIZE = 1000;
    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    public MessageService(MessageMapper messageMapper, MessageRepository messageRepository, ChatRepository chatRepository) {
        this.messageMapper = messageMapper;
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
    }

    public void checkPermissionToSend(MessageDto messageDto) {
        Chat chat = chatRepository.findById(messageDto.getChatId()).orElseThrow(ChatNotFoundException::new);
        boolean isLoggedUserParticipantOfChat = chat.getParticipants().stream()
                .map(User::getId)
                .anyMatch(userId -> userId.equals(getLoggedUserId()));
        if (!isLoggedUserParticipantOfChat) {
            throw new UnauthorizedResourceAccessException();
        }
    }

    public void checkDurationMessage(MessageDto message) {
        if (message.getText().isEmpty()) {
            throw new EmptyMessageException();
        }
        if (message.getText().length() > MAX_MESSAGE_SIZE) {
            throw new MessageToLongException();
        }
        if (message.getText().isBlank()) {
            throw new MessageWhiteSpacesException();
        }
    }

    public void save(MessageDto messageDto) {
        Message message = messageMapper.map(messageDto);
        messageRepository.save(message);
    }

    public Set<MessageDto> getChatMessages(long chatId) {
        return messageRepository.findAllByChat_Id(chatId).stream()
                .map(messageMapper::map)
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(MessageDto::getSendTime))));

    }

    public void checkAccessToGetMessages(Long chatId) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(ChatNotFoundException::new);
        boolean isAccessToGet = chat.getParticipants().stream()
                .map(User::getId)
                .anyMatch(id -> id == getLoggedUserId());
        if (!isAccessToGet) {
            throw new UnauthorizedResourceAccessException();
        }
    }
}
