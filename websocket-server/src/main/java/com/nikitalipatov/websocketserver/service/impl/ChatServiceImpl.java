package com.nikitalipatov.websocketserver.service.impl;

import com.nikitalipatov.websocketserver.controller.ChatController;
import com.nikitalipatov.websocketserver.dto.ChatDto;
import com.nikitalipatov.websocketserver.model.Chat;
import com.nikitalipatov.websocketserver.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatServiceImpl implements ChatService {

    private final SimpMessagingTemplate messagingTemplate;

    public void createChat(String chatName) {

        log.info(String.format("Chat \"%s\" created.", chatName));

        Chat chat = Chat.builder()
                .name(chatName)
                .build();

        messagingTemplate.convertAndSend(
                ChatController.FETCH_CREATE_CHAT_EVENT,
                ChatDto.builder()
                        .id(1)
                        .name(chatName)
                        .build());
    }

    public void deleteChat(String chatId) {

        getChats()
                .filter(chat -> Objects.equals(chatId, chat.getId()))
                .findAny()
                .ifPresent(chat -> {
                    log.info(String.format("Chat \"%s\" deleted.", chat.getName()));
                    messagingTemplate.convertAndSend(
                            ChatController.FETCH_DELETE_CHAT_EVENT,
                            ChatDto.builder()
                                    .id(1)
                                    .name(chat.getName())
                                    .build());
                });
    }

    public Stream<Chat> getChats() {
//        return Optional
//                .ofNullable(setOperations.members(KEY))
//                .orElseGet(HashSet::new)
//                .stream();
        return null;
    }
}
