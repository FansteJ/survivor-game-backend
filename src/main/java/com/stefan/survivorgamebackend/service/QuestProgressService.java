package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.dto.QuestProgressDTO;
import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.model.UserQuest;
import com.stefan.survivorgamebackend.repository.UserQuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestProgressService {
    private final UserQuestRepository userQuestRepository;

    public void updateQuestProgress(UserProfile profile, QuestProgressDTO progress) {
        List<UserQuest> quests = userQuestRepository.findAllByUserProfileAndDate(profile, LocalDate.now());
        for(UserQuest quest : quests) {
            if(quest.isCompleted())
                continue;
            switch(quest.getQuestType().getGoalType()){
                case PLAY_RUN -> {
                    quest.setProgress(quest.getProgress() + 1);
                }
                case KILL_ENEMIES -> {
                    quest.setProgress(quest.getProgress() + progress.enemiesKilled());
                }
                case EARN_GOLD -> {
                    quest.setProgress(quest.getProgress() + progress.goldEarned());
                }
                case REACH_LEVEL -> {
                    quest.setProgress(Math.max(quest.getProgress(), progress.levelReached()));
                }
            }
            if(quest.getQuestType().getGoal() <= quest.getProgress()) {
                quest.setCompleted(true);
            }
        }
        userQuestRepository.saveAll(quests);
    }
}
