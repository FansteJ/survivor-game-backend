package com.stefan.survivorgamebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_profiles", indexes = {
        @Index(name = "idx_leaderboard", columnList = "levelReached, totalXp")
})
public class UserProfile {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn
    private User user;

    @OneToMany(mappedBy = "profile")
    private List<GameSession> sessions;

    private int level;
    private long totalXp;
    private long gold;
    private long gems;

    private int totalRuns;
    private int levelReached;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
