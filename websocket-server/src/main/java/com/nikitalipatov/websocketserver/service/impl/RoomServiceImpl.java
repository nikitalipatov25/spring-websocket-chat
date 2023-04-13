package com.nikitalipatov.websocketserver.service.impl;

import com.nikitalipatov.common.dto.Participant;
import com.nikitalipatov.common.dto.ParticipantDto;
import com.nikitalipatov.common.dto.Room;
import com.nikitalipatov.websocketserver.repository.RoomRepository;
import com.nikitalipatov.websocketserver.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class RoomServiceImpl implements RoomService {

//    private static final Map<String, Participant> sessionIdToParticipantMap = new ConcurrentHashMap<>();
//    private static final Map<String, Room> roomMap = new ConcurrentHashMap<>();
//    private final SetOperations<String, Participant> setOperations;
//    private final RoomRepository roomRepository;
    private final RoomRepository roomRepository;

    public Room createRoom(String roomName) {
        var room = Room.builder().name(roomName).build();
        roomRepository.save(room);
        log.info("Room {} created ", roomName);
        return room;
    }

    public void addParticipantToRoom(String roomId, ParticipantDto participantDto) {
        var participant = Participant.builder().name(participantDto.getName()).build();
        var room = getRoom(roomId);
        room.getParticipants().add(participant);
        roomRepository.save(room);
        log.info("Participant {} added to room {}", participantDto.getName(), roomId);
    }

    public void deleteParticipantFromRoom(String roomId, ParticipantDto participantDto) {
        var room = getRoom(roomId);
        room.getParticipants().removeIf(p -> p.getId().equals(participantDto.getId()));
        log.info("Participant {} removed from room {}", participantDto.getName(), roomId);
        if (room.getParticipants().size() == 0) {
            roomRepository.deleteRoom(roomId);
            log.info("No participants in room {} . Room deleted", roomId);
        }
    }

    public Room getRoom(String roomId) {
        return roomRepository.findItemById(roomId);
    }

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }
}
