package com.nikitalipatov.websocketserver.example;

import com.nikitalipatov.common.dto.MessageDto;
import com.nikitalipatov.websocketserver.controller.ws.RoomWsController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisMessageSubscriber implements MessageListener {

    public static List<String> messageList = new ArrayList<>();
    private final SimpMessagingTemplate messagingTemplate;

    public RedisMessageSubscriber(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        messageList.add(message.toString());
        System.out.println("Received " + message.toString());
        messagingTemplate.convertAndSend("/topic/room/ba16e6c5-3f3d-4051-b2f6-be212057bf41" , MessageDto.builder().message(message.toString()).build());
        //roomWsController.sendMessage("ba16e6c5-3f3d-4051-b2f6-be212057bf41", MessageDto.builder().message(message.toString()).build());
    }
}
