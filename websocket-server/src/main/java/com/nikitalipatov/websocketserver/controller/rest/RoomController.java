package com.nikitalipatov.websocketserver.controller.rest;

import com.nikitalipatov.common.dto.MessageDto;
import com.nikitalipatov.common.dto.ParticipantDto;
import com.nikitalipatov.common.dto.Room;
import com.nikitalipatov.common.dto.RoomDto;
import com.nikitalipatov.common.feign.RoomClient;
import com.nikitalipatov.websocketserver.controller.ws.RoomWsController;
import com.nikitalipatov.websocketserver.example.RedisMessagePublisher;
import com.nikitalipatov.websocketserver.service.impl.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/room")
public class RoomController implements RoomClient {

    private final RoomServiceImpl roomService;
    private final RoomWsController roomWsController;

    private final RedisMessagePublisher redisMessagePublisher;

    @PostMapping(value = "/create")
    public Room createRoom(@RequestBody RoomDto roomDto) {
        return roomService.createRoom(roomDto.getName());
    }

    @PutMapping(value = "/{roomId}/join")
    public void joinRoom(@PathVariable String roomId, @RequestBody ParticipantDto participantDto) {
        roomWsController.joinRoom(roomId, participantDto);
    }

    @PutMapping(value = "/{roomId}/left")
    public void leftRoom(@PathVariable String roomId, @RequestBody ParticipantDto participantDto) {
        roomWsController.leaveRoom(roomId, participantDto);
    }

    @PostMapping(value = "/{roomId}/send")
    public void sendMessage(@PathVariable String roomId, @RequestBody MessageDto messageDto) {
        redisMessagePublisher.publish(messageDto.getMessage());
//        roomWsController.sendMessage(roomId, messageDto);
    }

    @GetMapping(value = "/subscribe/{roomId}")
    public void subscribeToRoom(@PathVariable String roomId) {
        roomWsController.subscribeToChat(roomId);
    }

    @GetMapping(value = "/list")
    public List<Room> getRooms() {
        return roomService.getRooms();
    }
}
