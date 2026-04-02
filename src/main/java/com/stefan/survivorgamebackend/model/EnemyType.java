package com.stefan.survivorgamebackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enemy_types")
@Getter
@Setter
@NoArgsConstructor
public class EnemyType {
    @Id
    private String id;

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
