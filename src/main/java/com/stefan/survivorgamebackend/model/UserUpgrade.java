package com.stefan.survivorgamebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(
        columnNames = {"user_profile_id", "upgrade_type_id"}))
public class UserUpgrade {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private UserProfile userProfile;

    @ManyToOne
    private UpgradeType upgradeType;

    private int level;
}
