package com.stefan.survivorgamebackend.repository;

import com.stefan.survivorgamebackend.dto.TopPlayerProjection;
import com.stefan.survivorgamebackend.model.GameSession;
import com.stefan.survivorgamebackend.model.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface GameSessionRepository extends JpaRepository<GameSession, UUID> {
    Optional<GameSession> findByProfileAndGameOverFalse(UserProfile profile);

    @Query("SELECT p.user.username AS username, MAX(gs.durationSeconds) AS maxDuration " +
            "FROM GameSession gs JOIN gs.profile p " +
            "GROUP BY p.user.username " +
            "ORDER BY MAX(gs.durationSeconds) DESC")
    Page<TopPlayerProjection> findTopSessions(Pageable pageable);

    @Query("SELECT MAX(gs.durationSeconds) FROM GameSession gs WHERE gs.profile.id = :profileId")
    Integer findBestDurationForProfile(@Param("profileId") UUID profileId);

    @Query("SELECT COUNT(DISTINCT gs.profile.id) FROM GameSession gs WHERE gs.durationSeconds > :myBestDuration")
    long countPlayersWithBetterDuration(@Param("myBestDuration") int myBestDuration);
}
