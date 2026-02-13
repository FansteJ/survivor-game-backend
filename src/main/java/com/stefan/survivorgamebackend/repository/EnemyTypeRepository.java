package com.stefan.survivorgamebackend.repository;

import com.stefan.survivorgamebackend.model.EnemyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnemyTypeRepository extends JpaRepository<EnemyType, UUID> {
}
