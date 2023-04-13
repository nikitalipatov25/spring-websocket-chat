package com.nikitalipatov.common.feign;

import com.nikitalipatov.common.dto.MessageDto;
import com.nikitalipatov.common.dto.ParticipantDto;
import com.nikitalipatov.common.dto.Room;
import com.nikitalipatov.common.dto.RoomDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "socket")
public interface RoomClient {

    @PostMapping(value = "/api/room/create")
    Room createRoom(@RequestBody RoomDto roomDto);

    @GetMapping(value = "/api/room/list")
    List<Room> getRooms();

    @PutMapping(value = "/api/room/{roomId}/join")
    void joinRoom (@PathVariable String roomId, @RequestBody ParticipantDto participantDto);

    @PostMapping(value = "/api/room/{roomId}/send")
    void sendMessage(@PathVariable String roomId, @RequestBody MessageDto messageDto);
}
