package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.dto.LeaderboardEntryDTO;
import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.repository.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LeaderboardService {
    private final UserProfileRepository userProfileRepository;
    private final UserProfileService userProfileService;

    public List<LeaderboardEntryDTO> getTopPlayers(int page, int size){
        List<LeaderboardEntryDTO> leaderboard = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size, Sort.by("levelReached")
                .descending().and(Sort.by("totalXp").descending()));

        Page<UserProfile> currentPage = userProfileRepository.findAll(pageable);
        List<UserProfile> profiles = currentPage.getContent();

        long rank = ((long) page) * size + 1;
        UserProfile myProfile = userProfileService.getCurrentProfile();
        boolean alreadyRanked = false;
        for(UserProfile profile : profiles){
            if(profile.getId().equals(myProfile.getId())){
                alreadyRanked = true;
            }
            LeaderboardEntryDTO entry = new LeaderboardEntryDTO(rank, profile.getUser().getUsername(), profile.getLevelReached());
            leaderboard.add(entry);
            rank++;
        }
        if(!alreadyRanked){
            rank = userProfileRepository.countBetterPlayers(myProfile.getLevelReached(), myProfile.getTotalXp())+1;
            LeaderboardEntryDTO entry = new LeaderboardEntryDTO(rank, myProfile.getUser().getUsername(), myProfile.getLevelReached());
            leaderboard.add(entry);
        }

        return leaderboard;
    }
}
