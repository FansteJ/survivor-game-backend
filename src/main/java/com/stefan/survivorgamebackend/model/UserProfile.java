package com.stefan.survivorgamebackend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class UserProfile {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn
    private User user;

    private int level;
    private long totalXp;
    private long gold;
    private long gems;

    private int totalRuns;

    private LocalDateTime createdAt;
}
