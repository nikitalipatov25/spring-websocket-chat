package com.nikitalipatov.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Participant implements Serializable {

    @Builder.Default
    private String id = String.valueOf(UUID.randomUUID());
    private String name;
}
