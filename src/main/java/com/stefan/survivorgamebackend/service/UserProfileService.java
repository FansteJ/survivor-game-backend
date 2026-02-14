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

    public UserProfileDTO getMyProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }

        Optional<UserProfile> userProfile = userProfileRepository.findByUser(user.get());
        if (!userProfile.isPresent()) {
            throw new UsernameNotFoundException("User profile not found");
        }

        UserProfile profile = userProfile.get();

        return new UserProfileDTO(profile.getId(), profile.getLevel(),
                profile.getTotalXp(), profile.getGold(), profile.getGems(), profile.getTotalRuns(), profile.getLevelReached());
    }
}
