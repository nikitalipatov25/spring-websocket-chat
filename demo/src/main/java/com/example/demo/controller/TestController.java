package com.example.demo.controller;

import com.nikitalipatov.common.dto.MessageDto;
import com.nikitalipatov.common.dto.ParticipantDto;
import com.nikitalipatov.common.dto.Room;
import com.nikitalipatov.common.dto.RoomDto;
import com.nikitalipatov.common.feign.RoomClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final RoomClient roomClient;

    @GetMapping("/test")
    public List<Room> some() {
        return roomClient.getRooms();
    }

    @PostMapping(value = "/test/{name}")
    public void some(@PathVariable String name) {
        var some = RoomDto.builder().name(name).build();
        roomClient.createRoom(some);
    }

    @PutMapping(value = "/test/join/{roomId}")
    public void joinRoomTest(@PathVariable String roomId, @RequestBody ParticipantDto participantDto) {
        roomClient.joinRoom(roomId, participantDto);
    }

    @PostMapping(value = "/test/{roomId}/send")
    public void sendMessage(@PathVariable String roomId, @RequestBody MessageDto messageDto) {
        roomClient.sendMessage(roomId, messageDto);
    }
}
