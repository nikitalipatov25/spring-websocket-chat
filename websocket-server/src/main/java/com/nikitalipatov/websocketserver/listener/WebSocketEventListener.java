package com.nikitalipatov.websocketserver.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessagingTemplate messagingTemplate;

//    @EventListener
//    public void handleSessionSubscribe(SessionSubscribeEvent event) {
//        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        var destination = headers.getDestination();
//        messagingTemplate.convertAndSend(Objects.requireNonNull(destination), "user joined");
//    }
//
//    @EventListener
//    public void handleSessionUnsubscribe(SessionUnsubscribeEvent event) {
//        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        var destination = headers.getDestination();
//        messagingTemplate.convertAndSend(Objects.requireNonNull(destination), "user unsub");
//    }
//
//    @EventListener
//    public void handleSessionDisconnect(SessionDisconnectEvent event) {
//        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        var sessionId = headers.getSessionId();
//        messagingTemplate.convertAndSend(Objects.requireNonNull(sessionId), "user left");
//    }
}
