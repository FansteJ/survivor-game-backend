package com.stefan.survivorgamebackend.repository;

import com.stefan.survivorgamebackend.model.QuestType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuestTypeRepository extends JpaRepository<QuestType, UUID> {
}
