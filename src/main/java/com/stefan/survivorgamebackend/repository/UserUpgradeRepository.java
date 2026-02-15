package com.stefan.survivorgamebackend.repository;

import com.stefan.survivorgamebackend.model.UpgradeType;
import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.model.UserUpgrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserUpgradeRepository extends JpaRepository<UserUpgrade, UUID> {
    Optional<UserUpgrade> findByUserProfileAndUpgradeType(UserProfile userProfile, UpgradeType upgradeType);
}
