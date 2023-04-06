package com.nikitalipatov.chatclient.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
//    private MessageType type;
    private String content;
    private String sender;

//    public enum MessageType {
//        CHAT,
//        JOIN,
//        LEAVE
//    }
}
