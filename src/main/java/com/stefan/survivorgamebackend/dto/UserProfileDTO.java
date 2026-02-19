package com.stefan.survivorgamebackend.dto;

import java.util.UUID;

public record UserProfileDTO (UUID id, String username, int level, long currentXp, long neededXp, long gold, long gems, int totalRuns, int bestLevel){
}
