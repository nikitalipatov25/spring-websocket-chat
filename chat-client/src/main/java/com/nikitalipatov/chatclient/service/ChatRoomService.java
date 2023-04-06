package com.nikitalipatov.chatclient.service;

import com.nikitalipatov.chatclient.dto.ChatRoomDto;
import com.nikitalipatov.chatclient.model.ChatMessage;
import com.nikitalipatov.chatclient.model.ChatRoom;

public interface ChatRoomService {

    ChatRoom createRoom(ChatRoomDto chatRoomDto);
    void sendMessage(ChatMessage chatMessage, String room);
}
