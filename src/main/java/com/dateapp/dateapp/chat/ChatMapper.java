package com.dateapp.dateapp.chat;

import com.dateapp.dateapp.match.MatchDto;
import com.dateapp.dateapp.match.MatchMapper;
import com.dateapp.dateapp.message.Message;
import com.dateapp.dateapp.user.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class ChatMapper {
    private final MatchMapper matchMapper;
    private static final String DEFAULT_MESSAGE = "You can start conversation.";
    private static final LocalDateTime DEFAULT_DATETIME =
            LocalDateTime.of(2023, 1, 1, 0, 0, 0);

    ChatMapper(MatchMapper matchMapper) {
        this.matchMapper = matchMapper;
    }

    ChatDto map(Chat chat) {
        ChatDto chatDto = new ChatDto();
        chatDto.setId(chat.getId());
        Set<Long> participantsIds = chat.getParticipants().stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        chatDto.setParticipantsIds(participantsIds);
        chatDto.setMatchDate(chat.getMatch().get(0).getMatchDate());
        ArrayList<MatchDto> matchesDtos = chat.getMatch().stream()
                .map(matchMapper::map)
                .collect(Collectors.toCollection(ArrayList::new));
        chatDto.setMatchDtos(matchesDtos);
        setLastMessage(chat, chatDto);
        return chatDto;
    }

    private static void setLastMessage(Chat chat, ChatDto chatDto) {
        TreeSet<Message> messages = chat.getMessages().stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Message::getSendTime))));
        Message lastMessage = null;
        if (!messages.isEmpty()) {
            int lastMessageIndex = messages.size() - 1;
            lastMessage = (Message) messages.toArray()[lastMessageIndex];
        }
        if (!messages.isEmpty() && lastMessage != null) {

            chatDto.setLastMessage(lastMessage.getText());
            chatDto.setLastMessageTime(lastMessage.getSendTime());
        } else {
            chatDto.setLastMessage(DEFAULT_MESSAGE);
            chatDto.setLastMessageTime(DEFAULT_DATETIME);
        }
    }
}
