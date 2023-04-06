package com.nikitalipatov.chatclient.controller.rest;

import com.nikitalipatov.chatclient.dto.ChatRoomDto;
import com.nikitalipatov.chatclient.model.ChatMessage;
import com.nikitalipatov.chatclient.model.ChatRoom;
import com.nikitalipatov.chatclient.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/room")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping(value = "/create")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ChatRoom createRoom(@RequestBody ChatRoomDto chatRoomDto) {
        return chatRoomService.createRoom(chatRoomDto);
    }

    @PostMapping(value = "/send/{room}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void sendMessage(@RequestBody ChatMessage chatMessage, @PathVariable String room) {
        chatRoomService.sendMessage(chatMessage, room);
    }
}
