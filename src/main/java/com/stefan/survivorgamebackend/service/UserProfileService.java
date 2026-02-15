package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.dto.UserProfileDTO;
import com.stefan.survivorgamebackend.model.User;
import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.repository.UserProfileRepository;
import com.stefan.survivorgamebackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserProfileService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public UserProfileDTO getMyProfileDTO() {
        UserProfile profile = getCurrentProfile();

        return new UserProfileDTO(profile.getId(), profile.getLevel(),
                profile.getTotalXp(), profile.getGold(), profile.getGems(), profile.getTotalRuns(), profile.getLevelReached());
    }

    public UserProfile getCurrentProfile(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        UserProfile profile = userProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("User profile not found"));
        return profile;
    }
}
