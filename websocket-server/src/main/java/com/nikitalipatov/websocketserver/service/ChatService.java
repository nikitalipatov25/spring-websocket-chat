package com.nikitalipatov.websocketserver.service;

import com.nikitalipatov.websocketserver.model.Chat;

import java.util.stream.Stream;

public interface ChatService {

    void createChat(String chatName);
    void deleteChat(String chatId);
    Stream<Chat> getChats();
}
