package com.stefan.survivorgamebackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EnemyType {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private long baseGold;
    private long baseXp;
    private long baseGems;

    public EnemyType(String name, long baseGold, long baseXp, long baseGems) {
        this.name = name;
        this.baseGold = baseGold;
        this.baseXp = baseXp;
        this.baseGems = baseGems;
    }
}
