package com.nikitalipatov.chatclient.config;

import com.nikitalipatov.chatclient.service.impl.SessionHandler;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Configuration
public class StomSessionConfig {

    @Bean
    @SneakyThrows
    public StompSession stompSession() {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        SessionHandler sessionHandler = new SessionHandler();
        var sessionAsync =
                stompClient.connect("ws://localhost:8090/websocket-server", sessionHandler);
        StompSession session = sessionAsync.get();
//        session.subscribe("/topic/room1", sessionHandler);
        return session;
    }
}
