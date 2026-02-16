package com.stefan.survivorgamebackend.repository;

import com.stefan.survivorgamebackend.model.User;
import com.stefan.survivorgamebackend.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
    Optional<UserProfile> findByUser(User user);
    @Query("SELECT COUNT(up) FROM UserProfile up WHERE " +
    "up.levelReached > :level OR " +
    "(up.levelReached = :level AND up.totalXp > :xp)")
    long countBetterPlayers(@Param("level") long level, @Param("xp") long xp);
}
