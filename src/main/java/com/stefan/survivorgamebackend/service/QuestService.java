package com.stefan.survivorgamebackend.service;

import com.stefan.survivorgamebackend.dto.QuestProgressDTO;
import com.stefan.survivorgamebackend.model.QuestType;
import com.stefan.survivorgamebackend.model.UserProfile;
import com.stefan.survivorgamebackend.model.UserQuest;
import com.stefan.survivorgamebackend.repository.QuestTypeRepository;
import com.stefan.survivorgamebackend.repository.UserProfileRepository;
import com.stefan.survivorgamebackend.repository.UserQuestRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestService {
    private final UserQuestRepository userQuestRepository;
    private final QuestTypeRepository questTypeRepository;
    private final UserProfileRepository userProfileRepository;

    private static final int DAILY_QUEST_COUNT = 3;

    @Transactional
    public List<UserQuest> getDailyQuests(UserProfile profile) {
        LocalDate today = LocalDate.now();

        List<UserQuest> dailyQuests = userQuestRepository.findAllByProfileAndDate(profile, today);
        if(!dailyQuests.isEmpty()) {
            return dailyQuests;
        }
        return generateNewQuests(profile, today);
    }

    private List<UserQuest> generateNewQuests(UserProfile profile, LocalDate date) {
        List<QuestType> questTypes = questTypeRepository.findAll();
        if(questTypes.isEmpty()) {
            throw new RuntimeException("No quest types found");
        }

        Collections.shuffle(questTypes);
        List<QuestType> selectedTypes = questTypes.stream().limit(DAILY_QUEST_COUNT).toList();

        List<UserQuest> dailyQuests = selectedTypes.stream().map(type -> createQuestFromType(profile, type, date)).toList();
        userQuestRepository.saveAll(dailyQuests);
        return dailyQuests;
    }

    private UserQuest createQuestFromType(UserProfile profile, QuestType type, LocalDate date) {
        UserQuest userQuest = new UserQuest();
        userQuest.setProfile(profile);
        userQuest.setQuestType(type);
        userQuest.setDate(date);
        userQuest.setProgress(0);
        userQuest.setCompleted(false);
        userQuest.setClaimed(false);
        return userQuest;
    }

    @Transactional
    public void updateQuestProgress(UserProfile profile, QuestProgressDTO progress) {
        List<UserQuest> quests = getDailyQuests(profile);
        boolean changed = false;

        for(UserQuest quest : quests) {
            if(quest.isCompleted())
                continue;
            long oldProgress = quest.getProgress();
            long newProgress = oldProgress;

            switch(quest.getQuestType().getGoalType()){
                case PLAY_RUN -> {
                    newProgress++;
                }
                case KILL_ENEMIES -> {
                    newProgress += progress.enemiesKilled();
                }
                case EARN_GOLD -> {
                    newProgress += progress.goldEarned();
                }
                case REACH_LEVEL -> {
                    newProgress = Math.max(quest.getProgress(), progress.levelReached());
                }
            }
            long goal = quest.getQuestType().getGoal();
            if(newProgress >= goal) {
                newProgress = goal;
                quest.setCompleted(true);
            }
            if(newProgress != oldProgress) {
                quest.setProgress(newProgress);
                changed = true;
            }
        }
        if(changed) {
            userQuestRepository.saveAll(quests);
        }
    }

    @Transactional
    public void claimReward(UUID questId, UserProfile profile) {
        UserQuest userQuest = userQuestRepository.findById(questId).orElse(null);
        if(userQuest == null) {
            throw new RuntimeException("Quest not found");
        }
        if(!userQuest.getProfile().getId().equals(profile.getId())) {
            throw new RuntimeException("You cannot claim this quest");
        }
        if(userQuest.isClaimed()){
            throw new RuntimeException("Quest is already claimed");
        }
        if(!userQuest.isCompleted()){
            throw new RuntimeException("Quest is not completed");
        }
        userQuest.setClaimed(true);
        profile.setGems(profile.getGems() + userQuest.getQuestType().getRewardGems());
        userQuestRepository.save(userQuest);
        userProfileRepository.save(profile);
    }
}
