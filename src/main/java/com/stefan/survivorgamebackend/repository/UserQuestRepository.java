package com.stefan.survivorgamebackend.repository;

import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.model.UserQuest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface UserQuestRepository extends JpaRepository<UserQuest, UUID> {
    List<UserQuest> findAllByProfileAndDate(UserProfile profile, LocalDate date);
}
