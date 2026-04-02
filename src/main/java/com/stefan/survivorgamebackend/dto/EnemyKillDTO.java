package com.stefan.survivorgamebackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnemyKillDTO {
    private String enemyTypeId;
    private int count;
}
