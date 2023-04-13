package com.nikitalipatov.websocketserver.controller.ws;


import com.nikitalipatov.common.dto.MessageDto;
import com.nikitalipatov.common.dto.ParticipantDto;
import com.nikitalipatov.websocketserver.service.impl.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RoomWsController {

//    public static final String CREATE_CHAT = "/topic/chats.create";
//    public static final String FETCH_CREATE_CHAT_EVENT = "/topic/chats.create.event";
//    public static final String FETCH_DELETE_CHAT_EVENT = "/topic/chats.delete.event";
//    public static final String FETCH_MESSAGES = "/topic/chats.{chat_id}.messages";

    private final SimpMessagingTemplate messagingTemplate;
//    private final ChatService chatService;
//    private final ParticipantServiceImpl participantService;
    private final RoomServiceImpl roomService;

    @MessageMapping("/topic/room/{roomId}")
    public void subscribeToChat(@DestinationVariable("chat") String chat) {
    }

    @MessageMapping("/topic/room/{roomId}/join")
    public void joinRoom(@DestinationVariable("roomId") String roomId, ParticipantDto participantDto) {
        roomService.addParticipantToRoom(roomId, participantDto);
        messagingTemplate.convertAndSend("/topic/room/" + roomId, "Participant " + participantDto.getName() +  " joined room");
    }

    @MessageMapping("/topic/room/{roomId}/send")
    public void sendMessage(@DestinationVariable("roomId") String roomId, MessageDto messageDto) {
        var room = roomService.getRoom(roomId);
        if (room.getParticipants()
                .stream()
                .anyMatch(participant -> participant.getName().equals(messageDto.getParticipantName()))) {
            messagingTemplate.convertAndSend("/topic/room/" + roomId, messageDto.getParticipantName() + " : " + messageDto.getMessage());
        } else {
            throw new RuntimeException("Participant is not in this room");
        }
    }

    @MessageMapping("/topic/room/{roomId}/leave")
    public void leaveRoom(@DestinationVariable("roomId") String roomId, ParticipantDto participantDto) {
        roomService.deleteParticipantFromRoom(roomId, participantDto);
        messagingTemplate.convertAndSend("/topic/room/" + roomId, "Participant " + participantDto.getName() +  " left room");
    }

}
