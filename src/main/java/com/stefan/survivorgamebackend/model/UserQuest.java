package com.stefan.survivorgamebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class UserQuest {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private UserProfile profile;

    @ManyToOne
    @JoinColumn(name = "quest_type_id")
    private QuestType questType;

    private long progress;

    private boolean completed;
    private boolean claimed;

    private LocalDateTime date;
}
