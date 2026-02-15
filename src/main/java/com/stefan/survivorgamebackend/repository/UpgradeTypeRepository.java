package com.stefan.survivorgamebackend.repository;

import com.stefan.survivorgamebackend.model.UpgradeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UpgradeTypeRepository extends JpaRepository<UpgradeType, UUID> {
}
