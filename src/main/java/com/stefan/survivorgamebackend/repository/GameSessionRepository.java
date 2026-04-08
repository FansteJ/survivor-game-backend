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

    // 2. Traži najdužu partiju za konkretnog igrača (tebe)
    @Query("SELECT MAX(gs.durationSeconds) FROM GameSession gs WHERE gs.profile.id = :profileId")
    Integer findBestDurationForProfile(@Param("profileId") UUID profileId);

    // 3. Broji koliko ima jedinstvenih igrača koji imaju bolju partiju od tvoje
    @Query("SELECT COUNT(DISTINCT p.id) FROM GameSession gs JOIN gs.profile p " +
            "GROUP BY p.id " +
            "HAVING MAX(gs.durationSeconds) > :myBestDuration")
    long countPlayersWithBetterDuration(@Param("myBestDuration") int myBestDuration);
}
