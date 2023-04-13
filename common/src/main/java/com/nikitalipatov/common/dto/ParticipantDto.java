package com.nikitalipatov.common.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantDto {

    private String id;
    private String name;
}
