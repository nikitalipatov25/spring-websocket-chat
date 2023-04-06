package com.nikitalipatov.chatclient.service.impl;

import com.nikitalipatov.chatclient.dto.ChatRoomDto;
import com.nikitalipatov.chatclient.model.ChatMessage;
import com.nikitalipatov.chatclient.model.ChatRoom;
import com.nikitalipatov.chatclient.repository.ChatRoomRepository;
import com.nikitalipatov.chatclient.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceIml implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final StompSession stompSession;

    @Override
    public ChatRoom createRoom(ChatRoomDto chatRoomDto) {
        var room = ChatRoom.builder().name(chatRoomDto.getName()).build();
        return chatRoomRepository.save(room);
    }

    @SneakyThrows
    public void sendMessage(ChatMessage chatMessage, String room) {
//        WebSocketStompClient client = new WebSocketStompClient(new StandardWebSocketClient());
//        StompSession session = client.connect("ws://localhost:8090/websocket-server", new SessionHandler()).get();
        stompSession.subscribe("/topic/" + room, new SessionHandler());
        stompSession.send("/app/chat/" + room + "/sendMessage", chatMessage);
    }
}
