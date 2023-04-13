package com.nikitalipatov.common.dto;

import com.nikitalipatov.common.dto.Participant;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Room")
public class Room implements Serializable {

    @Builder.Default
    private String id = String.valueOf(UUID.randomUUID());

    private String name;

    @Builder.Default
    private List<Participant> participants = new ArrayList<>();
}
