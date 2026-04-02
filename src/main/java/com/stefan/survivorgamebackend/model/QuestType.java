package com.stefan.survivorgamebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class QuestType {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private QuestGoalType goalType;

    private long goal;
    private long rewardGems;
}
