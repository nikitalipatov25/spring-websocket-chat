package com.nikitalipatov.websocketserver.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {

    private int id;

    private String name;
}
