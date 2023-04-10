//package com.nikitalipatov.websocketserver.controller;
//
//import com.nikitalipatov.websocketserver.trash.ChatMessage;
//import com.nikitalipatov.websocketserver.trash.impl.SessionHandler;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.messaging.simp.stomp.StompSession;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController(value = "/api/room")
//@RequiredArgsConstructor
//public class RestContr {
//
//    private final StompSession stompSession;
//
//    @PostMapping(value = "/send")
//    @ResponseStatus(value = HttpStatus.ACCEPTED)
//    public void sendMessage(@RequestBody ChatMessage chatMessage) {
//        var room = "test";
//        stompSession.subscribe("/topic/" + room, new SessionHandler());
//        stompSession.send("/app/chat/" + room + "/sendMessage", chatMessage);
//    }
//}
