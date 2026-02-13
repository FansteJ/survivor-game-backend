package com.stefan.survivorgamebackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class EnemyKillDTO {
    private UUID enemyTypeId;
    private int count;
}
