package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.dto.LeaderboardEntryDTO;
import com.stefan.survivorgamebackend.dto.TopPlayerProjection;
import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.repository.GameSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LeaderboardService {
    private final GameSessionRepository gameSessionRepository;
    private final UserProfileService userProfileService;

    public List<LeaderboardEntryDTO> getTopPlayers(int page, int size){
        List<LeaderboardEntryDTO> leaderboard = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, size);
        Page<TopPlayerProjection> currentPage = gameSessionRepository.findTopSessions(pageable);

        long rank = ((long) page) * size + 1;
        UserProfile myProfile = userProfileService.getCurrentProfile();
        boolean alreadyRanked = false;

        for(TopPlayerProjection projection : currentPage.getContent()){
            if(projection.getUsername().equals(myProfile.getUser().getUsername())){
                alreadyRanked = true;
            }

            LeaderboardEntryDTO entry = new LeaderboardEntryDTO(rank, projection.getUsername(), projection.getMaxDuration());
            leaderboard.add(entry);
            rank++;
        }

        if(!alreadyRanked){
            Integer myBestDuration = gameSessionRepository.findBestDurationForProfile(myProfile.getId());

            int duration = (myBestDuration != null) ? myBestDuration : 0;

            long myRank = gameSessionRepository.countPlayersWithBetterDuration(duration) + 1;
            LeaderboardEntryDTO entry = new LeaderboardEntryDTO(myRank, myProfile.getUser().getUsername(), duration);
            leaderboard.add(entry);
        }

        return leaderboard;
    }
}