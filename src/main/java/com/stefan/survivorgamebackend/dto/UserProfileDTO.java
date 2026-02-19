package com.stefan.survivorgamebackend.dto;

import java.util.UUID;

public record UserProfileDTO (UUID id, String username, int level, long totalXp, long gold, long gems, int totalRuns, int bestLevel){
}
